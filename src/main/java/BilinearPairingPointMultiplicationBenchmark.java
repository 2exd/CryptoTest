import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

/* 双线性映射点乘运算 */

public class BilinearPairingPointMultiplicationBenchmark {

    public static void main(String[] args) {
        // 初始化配对参数
        Pairing pairing = PairingFactory.getPairing("a.properties");

        // 创建配对群的元素
        Element g = pairing.getG1().newRandomElement();
        Element h = pairing.getG2().newRandomElement();

        // 随机选择一个标量作为点乘的因子
        Element scalar = pairing.getZr().newRandomElement();

        // 进行双线性对点乘运算并测量时间
        long totalExecutionTime = 0;

        for (int i = 0; i < 1000; i++) {
            long startTime = System.nanoTime();

            // 双线性对点乘运算
            Element result = g.duplicate().powZn(scalar);

            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            totalExecutionTime += executionTime;
        }

        // 计算平均耗时（毫秒）
        double averageExecutionTime = (double) totalExecutionTime / 1000.0;

        System.out.println("Average pairing-based point multiplication time: " + averageExecutionTime / 1e6 + " milliseconds");
    }
}
