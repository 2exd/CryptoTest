import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.math.ec.ECPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;

/* 椭圆曲线点加运算 */

public class ECCPointAdditionBenchmark {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        int numIterations = 1000; // 重复运行的次数
        long totalExecutionTime = 0;

        // 生成EC密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1"); // 曲线参数
        keyPairGenerator.initialize(ecSpec);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();

        // 获取基点
        ECNamedCurveParameterSpec namedCurveSpec = ECNamedCurveTable.getParameterSpec("secp256r1");
        ECPoint basePoint = namedCurveSpec.getG();

        for (int i = 0; i < numIterations; i++) {
            long startTime = System.nanoTime();

            // 执行椭圆曲线点加操作
            ECPoint result = basePoint.add(publicKey.getQ());

            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            totalExecutionTime += executionTime;
        }

        double averageExecutionTime = (double) totalExecutionTime / numIterations;
        System.out.println("Average ECC Point Addition Time: " + averageExecutionTime / 1e6 + " milliseconds");
    }
}
