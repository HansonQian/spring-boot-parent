package spring.boot.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.boot.mybatis.pojo.enums.SexEnum;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

@Configuration
public class BeanConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }


    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            //开启驼峰自动转换
            configuration.setMapUnderscoreToCamelCase(true);
            //别名处理器
            TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
            typeAliasRegistry.registerAliases("spring.boot.mybatis.pojo");
            //类型处理器
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            typeHandlerRegistry.register(SexEnum.class, new SexEnumTypeHandler());
            //添加自定义插件
            Interceptor plugin = new DemoPlugin();
            Properties properties = new Properties();
            properties.setProperty("key1", "value1");
            plugin.setProperties(properties);
            configuration.addInterceptor(plugin);
        };
    }

    /**
     * 自定义枚举类型处理器
     */
    public class SexEnumTypeHandler extends BaseTypeHandler<SexEnum> {

        //设置非空性别参数
        @Override
        public void setNonNullParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
            ps.setInt(i, parameter.getId());
        }

        //通过列名读取
        @Override
        public SexEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
            int sex = rs.getInt(columnName);
            if (sex != 1 && sex != 2) {
                return null;
            }
            return SexEnum.getEnumById(sex);
        }

        //通过下标读取
        @Override
        public SexEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
            int sex = rs.getInt(columnIndex);
            if (sex != 1 && sex != 2) {
                return null;
            }
            return SexEnum.getEnumById(sex);
        }

        //通过存储过程读取
        @Override
        public SexEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
            int sex = cs.getInt(columnIndex);
            if (sex != 1 && sex != 2) {
                return null;
            }
            return SexEnum.getEnumById(sex);
        }
    }

    /**
     * 自定义mybatis插件
     */
    @Intercepts({
            @Signature(type = StatementHandler.class,
                    method = "prepare",
                    args = {Connection.class, Integer.class})
    })
    public class DemoPlugin implements Interceptor {

        private Properties properties;

        //拦截方法逻辑
        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            System.out.println("插件拦截器方法....");
            return invocation.proceed();
        }

        //生成拦截器插件代理对象
        @Override
        public Object plugin(Object target) {
            return Plugin.wrap(target, this);
        }

        //设置插件属性
        @Override
        public void setProperties(Properties properties) {
            this.properties = properties;
        }
    }
}
