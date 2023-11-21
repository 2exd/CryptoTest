import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import java.security.SecureRandom;

/* 单向哈希运算 */

public class HashOperationBenchmark {
    public static void main(String[] args) {
        int numIterations = 1000; // 重复运行的次数
        long totalExecutionTime = 0;

        SecureRandom random = new SecureRandom();
        byte[] data = new byte[12]; // 输入数据的大小

        for (int i = 0; i < numIterations; i++) {
            random.nextBytes(data); // 生成随机输入数据

            long startTime = System.nanoTime();

            // 执行单向哈希操作（SHA-3）
            Digest sha3 = new SHA3Digest(256); // 256位的SHA-3
            sha3.update(data, 0, data.length);
            byte[] hash = new byte[sha3.getDigestSize()];
            sha3.doFinal(hash, 0);

            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            totalExecutionTime += executionTime;
        }

        double averageExecutionTime = (double) totalExecutionTime / numIterations;
        System.out.println("Average Hash Operation Time (SHA-3): " + averageExecutionTime / 1e6 + " milliseconds");
    }
}
