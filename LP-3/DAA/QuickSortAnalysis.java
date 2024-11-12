import java.util.Random;

public class QuickSortAnalysis {

    public static void quickSortDeterministic(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionDeterministic(arr, low, high);
            quickSortDeterministic(arr, low, pi - 1);
            quickSortDeterministic(arr, pi + 1, high);
        }
    }

    public static int partitionDeterministic(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void quickSortRandomized(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionRandomized(arr, low, high);
            quickSortRandomized(arr, low, pi - 1);
            quickSortRandomized(arr, pi + 1, high);
        }
    }

    public static int partitionRandomized(int[] arr, int low, int high) {
        Random rand = new Random();
        int pivotIndex = rand.nextInt(high - low + 1) + low;
        int temp = arr[pivotIndex];
        arr[pivotIndex] = arr[high];
        arr[high] = temp;
        
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void analyzeQuickSort(int[] arr) {
        System.out.println("Array size: " + arr.length);

        long deterministicTime = measureTime(QuickSortAnalysis::quickSortDeterministic, arr);
        System.out.printf("Deterministic Quick Sort Time: %.6f seconds%n", deterministicTime / 1e9);

        long randomizedTime = measureTime(QuickSortAnalysis::quickSortRandomized, arr);
        System.out.printf("Randomized Quick Sort Time: %.6f seconds%n", randomizedTime / 1e9);
    }

    public static long measureTime(SortFunction sortFunc, int[] arr) {
        int[] arrCopy = arr.clone();
        long startTime = System.nanoTime();
        sortFunc.sort(arrCopy, 0, arrCopy.length - 1);
        return System.nanoTime() - startTime;
    }

    @FunctionalInterface
    interface SortFunction {
        void sort(int[] arr, int low, int high);
    }

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 5000};
        Random rand = new Random();

        for (int size : sizes) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = rand.nextInt(10001);
            }
            System.out.println("\nTesting with array of size " + size);
            analyzeQuickSort(arr);
        }
    }
}
