import java.util.Arrays;

public class Algorithms {
    
    // Сортировка выбором - находит минимальный элемент и помещает его в начало
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    
    // Сортировка пузырьком - последовательно сравнивает и обменивает соседние элементы
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    // Сортировка вставками - вставляет каждый элемент в правильную позицию в отсортированной части
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    // Вспомогательная функция для сортировки слиянием
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        
        for (int p = 0; p < k; p++) {
            arr[left + p] = temp[p];
        }
    }
    
    // Сортировка слиянием - рекурсивно делит массив и объединяет отсортированные части
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    // Сортировка Шелла - улучшенная сортировка вставками с уменьшающимся шагом
    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }
    
    // Вспомогательная функция для быстрой сортировки
    private static int partition(int[] arr, int low, int high) {
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
    
    // Быстрая сортировка - выбирает опорный элемент и рекурсивно сортирует части
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    // Вспомогательная функция для пирамидальной сортировки
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] > arr[largest])
            largest = left;
        
        if (right < n && arr[right] > arr[largest])
            largest = right;
        
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }
    
    // Пирамидальная сортировка - строит кучу и извлекает максимальные элементы
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }
    
    // Линейный поиск - последовательно проверяет каждый элемент
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    // Бинарный поиск - работает только с отсортированными массивами
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    // Интерполяционный поиск - предсказывает позицию элемента
    public static int interpolationSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        
        while (low <= high && target >= arr[low] && target <= arr[high]) {
            if (low == high) {
                if (arr[low] == target) return low;
                return -1;
            }
            
            int pos = low + (((high - low) / 
                     (arr[high] - arr[low])) * (target - arr[low]));
            
            if (arr[pos] == target)
                return pos;
            else if (arr[pos] < target)
                low = pos + 1;
            else
                high = pos - 1;
        }
        return -1;
    }
    
    // Поиск Фибоначчи - использует числа Фибоначчи для определения позиций
    public static int fibonacciSearch(int[] arr, int target) {
        int n = arr.length;
        
        int fibM2 = 0; // F(m-2)
        int fibM1 = 1; // F(m-1)
        int fibM = fibM2 + fibM1; // F(m)
        
        while (fibM < n) {
            fibM2 = fibM1;
            fibM1 = fibM;
            fibM = fibM2 + fibM1;
        }
        
        int offset = -1;
        
        while (fibM > 1) {
            int i = Math.min(offset + fibM2, n - 1);
            
            if (arr[i] < target) {
                fibM = fibM1;
                fibM1 = fibM2;
                fibM2 = fibM - fibM1;
                offset = i;
            } else if (arr[i] > target) {
                fibM = fibM2;
                fibM1 = fibM1 - fibM2;
                fibM2 = fibM - fibM1;
            } else {
                return i;
            }
        }
        
        if (fibM1 != 0 && offset + 1 < n && arr[offset + 1] == target)
            return offset + 1;
        
        return -1;
    }
    
    // Функция для вывода массива
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    public static void main(String[] args) {
        // Тестовые данные
        int[] testArray = {64, 34, 25, 12, 22, 11, 90};
        int[] sortedArray = {11, 12, 22, 25, 34, 64, 90};
        int searchTarget = 25;
        
        System.out.println("Исходный массив: " + Arrays.toString(testArray));
        System.out.println("Отсортированный массив: " + Arrays.toString(sortedArray));
        System.out.println("Цель поиска: " + searchTarget);
        System.out.println();
        
        // Демонстрация сортировок
        System.out.println("=== АЛГОРИТМЫ СОРТИРОВКИ ===");
        
        int[] arr1 = testArray.clone();
        selectionSort(arr1);
        System.out.print("Сортировка выбором: ");
        printArray(arr1);
        
        int[] arr2 = testArray.clone();
        bubbleSort(arr2);
        System.out.print("Сортировка пузырьком: ");
        printArray(arr2);
        
        int[] arr3 = testArray.clone();
        insertionSort(arr3);
        System.out.print("Сортировка вставками: ");
        printArray(arr3);
        
        int[] arr4 = testArray.clone();
        mergeSort(arr4, 0, arr4.length - 1);
        System.out.print("Сортировка слиянием: ");
        printArray(arr4);
        
        int[] arr5 = testArray.clone();
        shellSort(arr5);
        System.out.print("Сортировка Шелла: ");
        printArray(arr5);
        
        int[] arr6 = testArray.clone();
        quickSort(arr6, 0, arr6.length - 1);
        System.out.print("Быстрая сортировка: ");
        printArray(arr6);
        
        int[] arr7 = testArray.clone();
        heapSort(arr7);
        System.out.print("Пирамидальная сортировка: ");
        printArray(arr7);
        
        System.out.println("\n=== АЛГОРИТМЫ ПОИСКА ===");
        
        System.out.println("Линейный поиск " + searchTarget + ": " + 
                         linearSearch(sortedArray, searchTarget));
        System.out.println("Бинарный поиск " + searchTarget + ": " + 
                         binarySearch(sortedArray, searchTarget));
        System.out.println("Интерполяционный поиск " + searchTarget + ": " + 
                         interpolationSearch(sortedArray, searchTarget));
        System.out.println("Поиск Фибоначчи " + searchTarget + ": " + 
                         fibonacciSearch(sortedArray, searchTarget));
    }
}
