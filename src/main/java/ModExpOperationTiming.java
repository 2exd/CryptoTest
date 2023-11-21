import java.math.BigInteger;
import java.security.SecureRandom;

/* 模幂运算 */

public class ModExpOperationTiming {
    public static void main(String[] args) {
        int numIterations = 1000; // 重复运行的次数
        long totalExecutionTime = 0;

        SecureRandom random = new SecureRandom();
        BigInteger base = new BigInteger(128, random);
        BigInteger exponent = new BigInteger(128, random);
        BigInteger modulus = new BigInteger(128, random);

        for (int i = 0; i < numIterations; i++) {
            long startTime = System.nanoTime();

            // 执行模幂运算操作
            BigInteger result = base.modPow(exponent, modulus);

            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            totalExecutionTime += executionTime;
        }

        double averageExecutionTime = (double) totalExecutionTime / numIterations;
        System.out.println("Average ModExp Operation Time: " + averageExecutionTime / 1e6 + " milliseconds");
    }
}
