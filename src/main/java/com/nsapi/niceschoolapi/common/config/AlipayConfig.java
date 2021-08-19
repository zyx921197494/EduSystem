package com.nsapi.niceschoolapi.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
//@ConfigurationProperties(prefix = "app")
public class AlipayConfig {

    /**
     * appId
     */
    @Value("2021000118604263")
    private String appId;

    /**
     * 商户私钥
     */
    @Value("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCb3yWfwvrJUDkw9oCgs1supKy3C2hvzQMZoMUJvlM2ZOXReW2pXPj/LApiRrxd8CqfbgSc/IA/lEfZ+AJV/RhfAEtLxEwQx91UZ182W9IZyG6DmKu98A2rZwslB69z8cPoQodAz9ArUB9qy8lhf+pmy7LsM14d3gg4TaZTe4QYRr0dol2EJ7Yn40UQ3CefFGFAKoqWXDfhYEteJ7GdwygINqkWvjxbOEd7x7cdnXARx4gyf8zypHEUolMaev0h2nij53sphlHoPC0GYr6ESAg1Y1ss+RjGZsqn/K6dRjG47GkCGxXEk+hl5xW/CLt5ZHU2QTYap4g9N2a+li+akWn9AgMBAAECggEAeCrf2bw7FcrIXKwd3EaKDHqeQPwFhvPZ6P8eCPX3Nx8wYn8oAQMRX8A3PS7ld9J5ObJa0iCESv/pKOzf+CUIje0vH3l1XxaG1tRe/gxp/MCeGswv013nW/Y0DFdRd6Sy8TOhDGFqTooVvoLffVBdiOSPyzOK4qHTWnzats+ptgBWtlSpvLxZXhXOF1X/MY+QJ5C0OZ2CLTB0X4pgOtIa36NTvtURbF3pwvIeIN0QcnDCWZ/4VXsegLDy1S5SOx4cCDaW+dIURwlH/IJGT1zwe+vE7Lq5wc2MibI8WWeZ6U169jK95CWdShSJmn7G/WIBDKl7msHSwq6xHoVfD48QYQKBgQDTkeX7bVxfkJ9KU3vkGpldmyO7+Luouq2uFtQ0awns3V5YAI30r8bH+J3xwYkBUe7YiZOYCvDzzk5TpNFszp4PcqekW6EofcpFP66gew7rZ+JBKA4+LMvUOBgvzo0Z16bQM4DH3tIfSxa2ynqgyG6Fo0Zx7TZIpjKiLtU78lzkZQKBgQC8muI6C0rO6PJP2JbgRr91PyYxILytCBdPH47fg2fTDQ+zMbzbY9e8PRhTWL/83tv4c93aReFnnsRNAunuJ6t/7OuiC/P6Ap+Xs5m1yI3lYgAxY8uDZx5BwkUMS2wYu082XFurBpuHbjGuLfmmUPMv/CdJdA+t8837RZF3xjCZuQKBgFLO0Rg2tqgE4APkZyJhstNrbHIfKbwhzObNsqMNniXigxGorecugXw1eARuvqQQoI6KUoV5CtQyVH/yi7GtsTnUo372Cj7h9TBje4L+h1lmIK1l8yUsvV7D0dusWZfay0TEU8xnDxEGf85ts8+Ig5G01Va0BMIsorbN49IBy4/tAoGAaBgn4hdXa4mAK3eEFjaV9xUqdBT4bZu4ma/2QACbrg1Ya/N4o7GgYUFrCp1CIUOzLpZsjMri1Dj5aPPBvvLgIdPWXXKxbnxCLw0uOfl972s+ZgQJT/X66hpZ16ZM+/1tg7hH08Xq0z2LZ/HyBsxI3GQUHuNSwBR+zD8iQXP+YOkCgYBun5vZOqmIodwcKC29NlfepXLbJ1X9Es2jMKpHCZYMJMvHOYD0u0iMiALOodj1Weq3hHVe4snURn6RQGCYdCA+BQ6G3kaJ3qlALIfvl/ncHP2aNFglepgFK5mgnzYTq2FD2Jrgknbi0qNuh7TpDLYbiQnthirpLsyxofFRYAPVJA==")
    private String privateKey;

    /**
     * 支付宝公钥
     */
    @Value("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhhYbj3ciUm9jOJSR1f6gzviW1ED9tjZuvE04IMM2Svx+dxasghlaAXPHT4lDqRfiQsoO6mB/YtZG26slCLlitvoli5rcaEUBhI9h804gJaPrQ/KjSdG3TwQUQhR7S8m/puBRQq8l8H5yDrtXlW6oT2tG30cSTGmcJ7L4L9pdJWjoTMoHDZzJJt+1Ro8n4YKmFvECC2sCDEdy7qNi58vY8VVYQT75nKZbJ2Aoh1YizVDC7jU4VZKeoTWd1entVWy6oTBSc1qMsQUeCa10eXOQNA7++LzaNYl+JdHM2rPyjeRzR3nxJ9y/hrrWqT90WU7QUo4LrdIiJtW4Toh8nSJ1iQIDAQAB")
    private String publicKey;

    /**
     * 服务器异步通知页面路径，需要公网能访问到
     */
    @Value("http://localhost:8080/index")
    private String notifyUrl;

    /**
     * 服务器同步通知页面路径，需要公网能访问到
     */
    @Value("http://localhost:8080/index")
    private String returnUrl;

    /**
     * 签名方式
     */
    @Value("RSA2")
    private String signType;

    /**
     * 字符编码格式
     */
    @Value("utf-8")
    private String charset;

    /**
     * 支付宝网关
     */
    @Value("https://openapi.alipaydev.com/gateway.do")
    private String gatewayUrl;

}
