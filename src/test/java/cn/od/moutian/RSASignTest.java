package cn.od.moutian;

import cn.od.moutian.util.Base64Util;
import cn.od.moutian.util.RSAUtil;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSASignTest extends BaseTest{
    private final RSAUtil rsaUtil = new RSAUtil();

    /**
     * 加载公私钥pem格式文件测试
     */
    @Test
    public void testReadKey() {
        final PublicKey publicKey = this.rsaUtil.loadPemPublicKey("rsa/public-key.pem");
        final PrivateKey privateKey = this.rsaUtil.loadPemPrivateKey("rsa/private-key.pem");
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);
    }

    /*
    * RSA 每次加密的结果都是不一样的！！！！！所以只能加密后的密文，我再解密。
    * */
    @Test
    public void testsign(){
        String cpid = "987654321";
        String key = "MIICojANBgkqhkiG9w0BAQEFAAOCAo8AMIICigKCAoEAgkAZfAgVCm2XWsIoia3xUYO7khqCjT7mbXxnn7hC+Q1JjruQAwATNl27hQBa79ARvxiz9QKzRY0rAK7+H49Q1XZSG8ZuZsZt25Zu+9tnB6rMCdyYJF3aBcCJ8CRHdT9aOpU0akxerCcLVoDeo/GekBXVPpvARmsEqfcL4AkrgsUtljIAsZL/xTTOWRYo4+DTIPJiHncOUr4LtoUT4iU00kfWfxtiWPVzsDh30qweISR2nqHv7NTzguHtLezTeM59OxV90Kr3AFOclMDWAEetN15BX/oaz4V64tt3LtGi/tl54bI898kAppH3nQUXEqhsNykiGpo14K6qrhfpBJ7jiRmRp5hZc894XuR06xXnYVxZIJ0UafhkIwCdsXzhUEBSVHJuH7r2pNsjn1SfAkiI2h/ufIzG++ST6GBwL+uKCucDUH+i0jaIB8oD1G1aGz8BtVMDBQwQWkvb37o4vnMuDOd153E3tFV/eoWZ5BYNMhFRs7LA51uRzeIGL9Rb+blJKnuNwD8ksnhTGGNAbfliEkg/zX1kWfWTbAlg68FQeX1qWB3QMzKeaYFJhC6qG2TiMiRK6n5fTd5UTL15f9qkjXtTas9y2A7a9HA9RyW3J6ilERiSnjdbENdDawnpVjcWIT0qqCwCF+CH5Pyw6NAq7/ReculmuxLiFgvFz7c6uGEQm/IXmtGlm8l77Hsj94+IWhc8HieEEICmsQej7tuUMDLQ+SYUBEqcpEYlFk2ySBbTamDi8QvTwOgpojNRKkzkuC56+qf8XExY5Rpjx3rV2feMXVMEIxnWSufrap2cWQZZwSn/+QIYK3jUP9ysfQvTO1xPh1yfYA0vgUgVtVer0QIDAQAB";
        RSAUtil rsaUtil = new RSAUtil();
        byte[] bytes = cpid.getBytes(StandardCharsets.UTF_8);
        byte[] signBytes = null;
        try {
            signBytes = rsaUtil.encrypt(bytes, rsaUtil.getPublicKey(key));
//            PdlNU4h+jpmL3hCNdmtbDhFFKQQdD8eRsRQik2P2ctGDhEVICQGhE+bZk4s+yJZ7b9lHMt1dMHYS6eSdGcuMAMaPRLFAiFVRSGLHEHUH7Q0a8TCl8CuUjCLQZTGNVAxY/hoafgIt0q7BePOU5DzRWlQX7d8sW6jAn4aAo2v97BcW8oNimcOy5ozTu9Qotxq1l9VJqojM9l27Uspx41C+PWjRhfYkaHLDsgCdEOhYE7aGAPb0KkZSn1VQfJkHNzsFxJy4gh919kHSez8CCqaQ9Fdu4JSXGCRTejVNUTSSSzL0jWX2tlyRa8xYYmpMBP3FsaHeXNOO4SVMxXB7HaeR7k5G6vEEGAITAEV/JhVxANeJnWjCBUACU0rOPudIBUeSE7/ywco6Q9CeiH3lvixMa8rvN27VoVnxUg4kWJ/FBxq9vGTG+dYLIfTWETZxhacnLO1q5NUtLwHJyYzzlvga6FD+dURUkVb5vf+DBBmyE7ZBU5VRqDWo0EQsBGHf28eu7jVBsVrTGowB0IkOaN1aJ6akfW9iiJ/+r5qkxTETLE2YIXUjchlhSHx+nTRUrL43kkTSaiHVtMRJWiF626DVaESJTOt/dTEa2W4Wx6gVkKoBUpS+rL1j2jL1p5x8YFNL2y7m2gwssXbriAW4zBaANvRrRcGqAz+w0AAsYWk3MPBtKedIFtwcinZ1i8wHIyyUE+OwwHIVqeV27f4xaNlyG6/vI3/uKZYbsLhki/qe3v3R/WRbH5wgRhpfanm/zohlkjYxWqqaRqRXrQzJfyD6zSrA6OMCWzu/IF4SRx8sDr5/I+asmyh9JH/Ttrj7LGAW06qijbogksaId+yKGh777Q==
//            HE+ldE33O548hklD2/ROpplGf46L9f0F0XOp/5c1vhZlvwslmK3yfMdrD+R4hw2bP5tYpaGrMpO9ApLolbGP9rfEjFTMx5aqFMJ+n8EJh1fQ0NQRk1F9qlmXNY9Twr4A1OnKTCX+WAICH/hLG29pAY8ru6Dw1kd9+7LtnGH6ub/LeFx/RdkiL3YCIqyBrX1PO7/NI2hc30VsIuvmTF+YoTBln6AE3c2hel/pGsej7P/5WhjBlQYxs2a6pqCODapHt55TP5COzA5QxATQTXWkfROW/ZCrVCaPzD6j/n+cY1pcyXjN4nHbHp4mpBEvguTpb/+/rNiXbFwyKt3+kL3PQva9a6vSm4qCiaTGYsYsirYjWGGpJDkNFkZPqcczhcz0zWMOv1uooU4rVhEue4FgcJDyjl8JPYkJkB+8cp+xwGFi+44AXcsSvdNCtSMtsfnUXPbUyPJXLekG+wOzLcZD7iecTEkY3oPRkPXsG+Ca2lzsuQKIerVgw9EAZlaKy3tW+aQWNu5+1wIqBN/6Pixn3aL4NlbGlXuoX9ZHYGHY1Jq6cs3lx3O9TIgh1Tn11VomMT9+MXH1SkC2aJqDFud99p0Vquk/N4k4CmI3Kni/aqYTkWkyqNKj0z6PU1qjSE0QjZ4yq7yFDL8swujWsu78MZ3uYibgvfhmXvAQs7FwaHiQ/rARt+7YjnW5QuGTp7VIsR7bn6OPgojHv4ZGd4vvbCE3VKpDEnYX4iAgYDlUi+F+URWALqesjyIMdTCQcMFHtYNf8VxBnnK4HZN7FWFJ/nOF17yrXiPpXE95p7Ufk4JIgUImvvhGLeM8R5PTnjqBxl5Y0ATWUARkhC/kRx9s5w==
//            O2aRxt/LOZxvase1hRsTmzH+6rMe5AA0gkxOv8sVli9CXjVPIiO99mF7pPWwsqPNZte9XC9aqWMjorcCNAb3MjZMSL89+vKE5xemtTi+g8kjtDMC6yasK5GhmEM0L/2QqLZAHzW7I7kzAEi6vgHiWnJYOQvuJGpt3/HIb02zhdLLMg+QhAwiwq/HoBfXpr7mqHmRk+2b4liIiVh5B57oiAMIImBtZLH8NEFcgEn3mZNANpN4mRkDBPAvfrusUw4O5WQe8QAf46XkdMR6aMIxbV7pSROumRPYyQosFVQduXPIVrGrlFzy0lRs6S7vMABgji54+PE0CYUXqcdvFkWncG5H9DBtYK0QZdGm02VggPmXMb2h/IbWUgMdBznIpNoIol7T4aYPsUJ4bhHv91a1lCz/50B5txUrIhhan45c4YQJe0rP9R+pc1cUk6OCgrpgSKUvM/ORauwdKh2nPcQBiD+rMiXFnp4jlvPMrzkQzC8I2Afe7zqEmWOw58keMwlqeKJ2L+ONLUeycN/YqSPHApeNdie41nXBSvYKxB1VyQx9vdr+iUXox3iGMSKFQwKcR2Cd2VGh+6J6+R5tKgdEvWheyioefEVyPb10K2U2aj+LINXjzUEf/2J4nbY30nIaSFyKO/WqKRV8OSSIYXcsEZeZ4sugWkVShhYrMfShEXCA6rsKZzK98aBFnRM2llImCHXENH9/1UXc8yfcvSl1OBgic30uyJQbg6uonC1vxblHg8ouq3Oz5+er+hRrtd6Gtg74EGyj0eDI5Vbcky3f97Qz7BWz1R3CiZvpKNXtP3fzjxx09H05P11aGpaI1/ZS8Z2wCK4YU7H/Vkkfpkda3Q==
            System.out.println(Base64Util.encode(signBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testcheck(){
        RSAUtil rsaUtil = new RSAUtil();
        String a = "PdlNU4h+jpmL3hCNdmtbDhFFKQQdD8eRsRQik2P2ctGDhEVICQGhE+bZk4s+yJZ7b9lHMt1dMHYS6eSdGcuMAMaPRLFAiFVRSGLHEHUH7Q0a8TCl8CuUjCLQZTGNVAxY/hoafgIt0q7BePOU5DzRWlQX7d8sW6jAn4aAo2v97BcW8oNimcOy5ozTu9Qotxq1l9VJqojM9l27Uspx41C+PWjRhfYkaHLDsgCdEOhYE7aGAPb0KkZSn1VQfJkHNzsFxJy4gh919kHSez8CCqaQ9Fdu4JSXGCRTejVNUTSSSzL0jWX2tlyRa8xYYmpMBP3FsaHeXNOO4SVMxXB7HaeR7k5G6vEEGAITAEV/JhVxANeJnWjCBUACU0rOPudIBUeSE7/ywco6Q9CeiH3lvixMa8rvN27VoVnxUg4kWJ/FBxq9vGTG+dYLIfTWETZxhacnLO1q5NUtLwHJyYzzlvga6FD+dURUkVb5vf+DBBmyE7ZBU5VRqDWo0EQsBGHf28eu7jVBsVrTGowB0IkOaN1aJ6akfW9iiJ/+r5qkxTETLE2YIXUjchlhSHx+nTRUrL43kkTSaiHVtMRJWiF626DVaESJTOt/dTEa2W4Wx6gVkKoBUpS+rL1j2jL1p5x8YFNL2y7m2gwssXbriAW4zBaANvRrRcGqAz+w0AAsYWk3MPBtKedIFtwcinZ1i8wHIyyUE+OwwHIVqeV27f4xaNlyG6/vI3/uKZYbsLhki/qe3v3R/WRbH5wgRhpfanm/zohlkjYxWqqaRqRXrQzJfyD6zSrA6OMCWzu/IF4SRx8sDr5/I+asmyh9JH/Ttrj7LGAW06qijbogksaId+yKGh777Q==";
        String b = "HE+ldE33O548hklD2/ROpplGf46L9f0F0XOp/5c1vhZlvwslmK3yfMdrD+R4hw2bP5tYpaGrMpO9ApLolbGP9rfEjFTMx5aqFMJ+n8EJh1fQ0NQRk1F9qlmXNY9Twr4A1OnKTCX+WAICH/hLG29pAY8ru6Dw1kd9+7LtnGH6ub/LeFx/RdkiL3YCIqyBrX1PO7/NI2hc30VsIuvmTF+YoTBln6AE3c2hel/pGsej7P/5WhjBlQYxs2a6pqCODapHt55TP5COzA5QxATQTXWkfROW/ZCrVCaPzD6j/n+cY1pcyXjN4nHbHp4mpBEvguTpb/+/rNiXbFwyKt3+kL3PQva9a6vSm4qCiaTGYsYsirYjWGGpJDkNFkZPqcczhcz0zWMOv1uooU4rVhEue4FgcJDyjl8JPYkJkB+8cp+xwGFi+44AXcsSvdNCtSMtsfnUXPbUyPJXLekG+wOzLcZD7iecTEkY3oPRkPXsG+Ca2lzsuQKIerVgw9EAZlaKy3tW+aQWNu5+1wIqBN/6Pixn3aL4NlbGlXuoX9ZHYGHY1Jq6cs3lx3O9TIgh1Tn11VomMT9+MXH1SkC2aJqDFud99p0Vquk/N4k4CmI3Kni/aqYTkWkyqNKj0z6PU1qjSE0QjZ4yq7yFDL8swujWsu78MZ3uYibgvfhmXvAQs7FwaHiQ/rARt+7YjnW5QuGTp7VIsR7bn6OPgojHv4ZGd4vvbCE3VKpDEnYX4iAgYDlUi+F+URWALqesjyIMdTCQcMFHtYNf8VxBnnK4HZN7FWFJ/nOF17yrXiPpXE95p7Ufk4JIgUImvvhGLeM8R5PTnjqBxl5Y0ATWUARkhC/kRx9s5w==";
        String c = "O2aRxt/LOZxvase1hRsTmzH+6rMe5AA0gkxOv8sVli9CXjVPIiO99mF7pPWwsqPNZte9XC9aqWMjorcCNAb3MjZMSL89+vKE5xemtTi+g8kjtDMC6yasK5GhmEM0L/2QqLZAHzW7I7kzAEi6vgHiWnJYOQvuJGpt3/HIb02zhdLLMg+QhAwiwq/HoBfXpr7mqHmRk+2b4liIiVh5B57oiAMIImBtZLH8NEFcgEn3mZNANpN4mRkDBPAvfrusUw4O5WQe8QAf46XkdMR6aMIxbV7pSROumRPYyQosFVQduXPIVrGrlFzy0lRs6S7vMABgji54+PE0CYUXqcdvFkWncG5H9DBtYK0QZdGm02VggPmXMb2h/IbWUgMdBznIpNoIol7T4aYPsUJ4bhHv91a1lCz/50B5txUrIhhan45c4YQJe0rP9R+pc1cUk6OCgrpgSKUvM/ORauwdKh2nPcQBiD+rMiXFnp4jlvPMrzkQzC8I2Afe7zqEmWOw58keMwlqeKJ2L+ONLUeycN/YqSPHApeNdie41nXBSvYKxB1VyQx9vdr+iUXox3iGMSKFQwKcR2Cd2VGh+6J6+R5tKgdEvWheyioefEVyPb10K2U2aj+LINXjzUEf/2J4nbY30nIaSFyKO/WqKRV8OSSIYXcsEZeZ4sugWkVShhYrMfShEXCA6rsKZzK98aBFnRM2llImCHXENH9/1UXc8yfcvSl1OBgic30uyJQbg6uonC1vxblHg8ouq3Oz5+er+hRrtd6Gtg74EGyj0eDI5Vbcky3f97Qz7BWz1R3CiZvpKNXtP3fzjxx09H05P11aGpaI1/ZS8Z2wCK4YU7H/Vkkfpkda3Q==";
        String key = "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";
        byte[] bytes = Base64Util.decode(a);
        byte[] source = null;
        try {
            source = rsaUtil.decrypt(bytes, rsaUtil.getPrivateKey(key));
            System.out.println("Base64Util.encode(source) : " + Base64Util.encode(source));
            System.out.println("new String(source) : " + new String(source));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//{timetamp=1536638001255, sdkversion=1.0.0, cpid=987654321, payflowNum=1,
// sign=PbkGedJy5jJcm3j7ASjb2O1qQGQ/g0rP7iAgF4HMQ9xcjcktkSifTILWLxhj5tdBbbz3NfOamXhuUvDuKHL4ApVOxTjmb2iPvj/xVx5/TjyQCRKVQFhC6l8SUwwNabQ/
// R5TShJG1Bs3vxVT2QI6tdOGNylq0VH34VL9qYKiiMXU=, token=1.0.0}
    @Test
    public void testcheck2(){
        RSAUtil rsaUtil = new RSAUtil();
        String a = "kucznTvfQeDiVLteFi1MDbKqMl7ojr02tmsntQAAYT21nqanFiWLC1QJ9GVmIrGmX+e+r4u2hrywqSt7DAZl6cOF2F62wVfVeSFendgI1p/ioq2BYRlOkPzK9INkS24568pFEKcNCU7aOxIat9jcMQi4Iin6ndls4rBWzy9uDbw=";
        String key = "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";
        byte[] bytes = Base64Util.decode(a);
        byte[] source = null;
        try {
            source = rsaUtil.decrypt(bytes, rsaUtil.getPrivateKey(key));
            System.out.println("Base64Util.encode(source) : " + Base64Util.encode(source));
            System.out.println("new String(source) : " + new String(source));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void num(){

        Integer a = 10;
        Integer b = 20;
        Integer c = 30;
        Integer d = 30;
        Integer e = 320;
        Integer f = 320;
        Long g = 30L;
        Long h = 20L;

        System.out.println(c==d);
        System.out.println(e==f);
        System.out.println(c==(a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g==(a+b));
        System.out.println(g.equals(a+b));
        System.out.println(g.equals(a+h));

    }
}