package com.chenzx.movie;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/7 13:42
 */
@EnableCaching
@SpringBootApplication
@MapperScan("com.chenzx.movie.mapper")
public class MovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }

    @Bean
    PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }

    @Bean
    public AlipayClient alipayClient() throws AlipayApiException {
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl("https://openapi.alipaydev.com/gateway.do");
        certAlipayRequest.setAppId("2021000118687065");
        certAlipayRequest.setPrivateKey("MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDR3zXMbiHtQqS+1Fd9jzzX8VuaW5eYRPAqhj9riTrUOVAbOlNfeP9d4nCyXwTwj6jUc1ibgAbmbx7vx2OsHQrqSLr7JG24oMykvdbPeIJKgSb5JWNbh3K2hcxCsElFFBd6xp2teA4WeghyvvQhUSJPbyfbJOnW94zn25gAyOnbms3xzEelip+Xn8UIymX7LNNJYIhwP2OPDgD+vqyohN0dbKvpEQLEJUO4zlw/5icjBhDvDnbgRvsgNi2BgbuPYUzFQBOEuN9eqndOkZO1mX0xD2fVDs9f/+eernd5C/gzh4D8UsfqHuL1X13qfy/iZwv+SALLZIv1JKELHyHGoN8PAgMBAAECggEBAKYN5DVCFpnxlwReYnM4OD3r/Dyfp8yVDym2WsutItz0FAvXFEyAIh2You6e+/uSz1R5h2xR2iPNXD8AFj8CI7bHoRpA8oeYwxK+Gg7CHOTyV3HeQUUUYJFGQB2r+e0sPn+oUu4EBbGHENQpfmQ2G92Z54EfLd4mtFhniVdloJNd8soNnful75qay7hv/Md0HsjlL3WoML9wW2J8FooVyE9mj+EZylu4bkJAX1njwNlzRyBqYMX71n7F5l0eO0DCWG8nBqw5i16RTBYE+oBSxInx6hcyY0vWZXNYdKaEUHGpHRMh7+PQmGpSCZ8HLF2cH4WfAqrXn4oBStvVPPU9xOECgYEA/RBK1Mb0p9GT5jGRsQJGzfTOwdcYRe8TTEzuYqToApo0V9mrYNskm6ohmLsUmD2R4dVuZXlj1Df6r65o4aDM4X0ULJ69D9MaNpudm08fzM1UewHtvg47K6xdoPZ15prbTO1tc8BeX6UZQq/4SuoU+pAJzL4PFk6wJvUcUugEpScCgYEA1E6er2FWCcfpGlq2n1nqL7lIevDBbDP7uAIPrKIvgiArviMJq3r5XBqmFMKBTz5MYSmDyIfpk9e2IRqXQ8PgkgUFnKaIdMs3knzb0PUlh5tgihmn6aowyZp35iVA6WPcB14ohntJv7KbBaEStiTrIJkh68qYSLnVU1NRQIuOt9kCgYAlZcP5P9Gl/0XBDfA+3VxDUXtAkB3pL9SFQRU1hLM4tzsnBGbcMGZvjqvaW0NG4Epvuk/PNSo4kXE9hyO+vDmq1QYnNeowrXzBNywKaJ4FcQ6cAwP0QzwhTx6jC+kOhDIGY0v6gCIyNj7OaRzjTUZ+ZMzd8fRe0pAbobtm0RzmnwKBgQCY7VTCkcNu4jAXirY3TIYzACykWd2JulwcLGfr+xtriQ7ZM9XYToll/dAZ7g1E7mQILOY/QR9QHjaT29qPtn2B0No8/3wVXPhXrVaNSrhQ2OwOLfLUjoH8BidNgRIjy4mHSRy52f7Cdb3SrTaUOz2eyo8zkEhnQmzWS5KggqY36QKBgQC8yssg3aE++0HjeDrgWBjoJcsB31APD0kdxV7OUhvDmNF9B0DTk6DtyhNIXOvRP+t2uiwinwl/lkLRlE499e6cgR5qJBjzv9/Qo5mC1A7mJw7RLcfm4/HsZulquh9ecrb7XLRmWxcyOPMyqYfnN5sSnYn5FyFIXehezcQZBRKRTA==");
        certAlipayRequest.setFormat("JSON");
        certAlipayRequest.setCharset("UTF-8");
        certAlipayRequest.setSignType("RSA2");
        certAlipayRequest.setCertPath("D:\\cert\\appCertPublicKey_2021000118687065.crt");
        certAlipayRequest.setAlipayPublicCertPath("D:\\cert\\alipayCertPublicKey_RSA2.crt");
        certAlipayRequest.setRootCertPath("D:\\cert\\alipayRootCert.crt");
        return new DefaultAlipayClient(certAlipayRequest);
    }
}
