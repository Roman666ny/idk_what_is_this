#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// Сортировка выбором - находит минимальный элемент и помещает его в начало
void selectionSort(vector<int>& arr) {
    int n = arr.size();
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        swap(arr[i], arr[minIndex]);
    }
}

// Сортировка пузырьком - последовательно сравнивает и обменивает соседние элементы
void bubbleSort(vector<int>& arr) {
    int n = arr.size();
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                swap(arr[j], arr[j + 1]);
            }
        }
    }
}

// Сортировка вставками - вставляет каждый элемент в правильную позицию в отсортированной части
void insertionSort(vector<int>& arr) {
    int n = arr.size();
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
void merge(vector<int>& arr, int left, int mid, int right) {
    vector<int> temp(right - left + 1);
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
void mergeSort(vector<int>& arr, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}

// Сортировка Шелла - улучшенная сортировка вставками с уменьшающимся шагом
void shellSort(vector<int>& arr) {
    int n = arr.size();
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
int partition(vector<int>& arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    
    for (int j = low; j < high; j++) {
        if (arr[j] <= pivot) {
            i++;
            swap(arr[i], arr[j]);
        }
    }
    swap(arr[i + 1], arr[high]);
    return i + 1;
}

// Быстрая сортировка - выбирает опорный элемент и рекурсивно сортирует части
void quickSort(vector<int>& arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

// Вспомогательная функция для пирамидальной сортировки
void heapify(vector<int>& arr, int n, int i) {
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    
    if (left < n && arr[left] > arr[largest])
        largest = left;
    
    if (right < n && arr[right] > arr[largest])
        largest = right;
    
    if (largest != i) {
        swap(arr[i], arr[largest]);
        heapify(arr, n, largest);
    }
}

// Пирамидальная сортировка - строит кучу и извлекает максимальные элементы
void heapSort(vector<int>& arr) {
    int n = arr.size();
    
    for (int i = n / 2 - 1; i >= 0; i--)
        heapify(arr, n, i);
    
    for (int i = n - 1; i > 0; i--) {
        swap(arr[0], arr[i]);
        heapify(arr, i, 0);
    }
}

// Линейный поиск - последовательно проверяет каждый элемент
int linearSearch(const vector<int>& arr, int target) {
    for (int i = 0; i < arr.size(); i++) {
        if (arr[i] == target) {
            return i;
        }
    }
    return -1;
}

// Бинарный поиск - работает только с отсортированными массивами
int binarySearch(const vector<int>& arr, int target) {
    int left = 0;
    int right = arr.size() - 1;
    
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
int interpolationSearch(const vector<int>& arr, int target) {
    int low = 0;
    int high = arr.size() - 1;
    
    while (low <= high && target >= arr[low] && target <= arr[high]) {
        if (low == high) {
            if (arr[low] == target) return low;
            return -1;
        }
        
        int pos = low + (((double)(high - low) / 
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
int fibonacciSearch(const vector<int>& arr, int target) {
    int n = arr.size();
    
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
        int i = min(offset + fibM2, n - 1);
        
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
    
    if (fibM1 && offset + 1 < n && arr[offset + 1] == target)
        return offset + 1;
    
    return -1;
}

// Функция для вывода массива
void printArray(const vector<int>& arr) {
    for (int i = 0; i < arr.size(); i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
}

int main() {
    // Тестовые данные
    vector<int> testArray = {64, 34, 25, 12, 22, 11, 90};
    vector<int> sortedArray = {11, 12, 22, 25, 34, 64, 90};
    int searchTarget = 25;
    
    cout << "Исходный массив: ";
    printArray(testArray);
    cout << "Отсортированный массив: ";
    printArray(sortedArray);
    cout << "Цель поиска: " << searchTarget << endl << endl;
    
    // Демонстрация сортировок
    cout << "=== АЛГОРИТМЫ СОРТИРОВКИ ===" << endl;
    
    vector<int> arr1 = testArray;
    selectionSort(arr1);
    cout << "Сортировка выбором: ";
    printArray(arr1);
    
    vector<int> arr2 = testArray;
    bubbleSort(arr2);
    cout << "Сортировка пузырьком: ";
    printArray(arr2);
    
    vector<int> arr3 = testArray;
    insertionSort(arr3);
    cout << "Сортировка вставками: ";
    printArray(arr3);
    
    vector<int> arr4 = testArray;
    mergeSort(arr4, 0, arr4.size() - 1);
    cout << "Сортировка слиянием: ";
    printArray(arr4);
    
    vector<int> arr5 = testArray;
    shellSort(arr5);
    cout << "Сортировка Шелла: ";
    printArray(arr5);
    
    vector<int> arr6 = testArray;
    quickSort(arr6, 0, arr6.size() - 1);
    cout << "Быстрая сортировка: ";
    printArray(arr6);
    
    vector<int> arr7 = testArray;
    heapSort(arr7);
    cout << "Пирамидальная сортировка: ";
    printArray(arr7);
    
    cout << "\n=== АЛГОРИТМЫ ПОИСКА ===" << endl;
    
    cout << "Линейный поиск " << searchTarget << ": " 
         << linearSearch(sortedArray, searchTarget) << endl;
    cout << "Бинарный поиск " << searchTarget << ": " 
         << binarySearch(sortedArray, searchTarget) << endl;
    cout << "Интерполяционный поиск " << searchTarget << ": " 
         << interpolationSearch(sortedArray, searchTarget) << endl;
    cout << "Поиск Фибоначчи " << searchTarget << ": " 
         << fibonacciSearch(sortedArray, searchTarget) << endl;
    
    return 0;
}
