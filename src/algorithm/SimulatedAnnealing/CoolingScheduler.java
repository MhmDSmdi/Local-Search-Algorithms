package algorithm.SimulatedAnnealing;

public interface CoolingScheduler {
    double possibility(int n);
    CoolingScheduler exponi = (int n) -> Math.exp(-n);
    CoolingScheduler logi = (int n) -> 1 / (1 + Math.log(1+n));
    CoolingScheduler reversi = (int n) -> 1 / (1 + n);
}

