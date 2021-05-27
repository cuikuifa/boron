package cn.od.moutian.service;

import cn.od.moutian.BaseTest;
import cn.od.moutian.util.RSAUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by zxw on 2018/8/6 0006.
 */
public class RoleServiceTest extends BaseTest {
    private final RSAUtil rsaUtil = new RSAUtil();

    @Autowired
    RoleService roleService;

    @Test
    public void test1() {
        final PublicKey publicKey = this.rsaUtil.loadPemPublicKey("rsa/public-key.pem");
        final PrivateKey privateKey = this.rsaUtil.loadPemPrivateKey("rsa/private-key.pem");
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

    }
}