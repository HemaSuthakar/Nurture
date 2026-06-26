public class Forecaster {

    public static double calculateFutureValue(
            double currentValue,
            double growthRate,
            int years) {

        // Base 
        if (years == 0) {
            return currentValue;
        }

        // Recursive 
        return calculateFutureValue(
                currentValue,
                growthRate,
                years - 1)
                * (1 + growthRate);
    }
}