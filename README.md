# Алгоритмы сортировки и поиска на C++

## 1. Сортировка выбором (Selection Sort)

### Описание
На каждом шаге алгоритм находит минимальный элемент в неотсортированной части массива и меняет его местами с первым элементом неотсортированной части.

### Код на C++
```cpp
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
```

### Временная сложность
- **Лучший случай:** O(n²)
- **Средний случай:** O(n²)  
- **Худший случай:** O(n²)

---

## 2. Сортировка пузырьком (Bubble Sort)

### Описание
Алгоритм многократно проходит по массиву, сравнивая соседние элементы и меняя их местами, если они стоят в неправильном порядке.

### Код на C++
```cpp
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
```

### Временная сложность
- **Лучший случай:** O(n) - когда массив уже отсортирован
- **Средний случай:** O(n²)
- **Худший случай:** O(n²)

---

## 3. Сортировка вставками (Insertion Sort)

### Описание
Алгоритм строит отсортированную часть массива, постепенно вставляя каждый новый элемент в правильную позицию.

### Код на C++
```cpp
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
```

### Временная сложность
- **Лучший случай:** O(n)
- **Средний случай:** O(n²)
- **Худший случай:** O(n²)

---

## 4. Сортировка слиянием (Merge Sort)

### Описание
Алгоритм разделяет массив пополам, рекурсивно сортирует каждую половину, затем объединяет отсортированные половины.

### Код на C++
```cpp
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

void mergeSort(vector<int>& arr, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}
```

### Временная сложность
- **Все случаи:** O(n log n)

---

## 5. Сортировка Шелла (Shell Sort)

### Описание
Улучшенная версия сортировки вставками, которая сортирует элементы, находящиеся на определенном расстоянии друг от друга.

### Код на C++
```cpp
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
```

### Временная сложность
- **Лучший случай:** O(n log n)
- **Худший случай:** O(n²)

---

## 6. Быстрая сортировка (Quick Sort)

### Описание
Алгоритм выбирает опорный элемент, разделяет массив на элементы меньше и больше опорного, затем рекурсивно сортирует части.

### Код на C++
```cpp
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

void quickSort(vector<int>& arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}
```

### Временная сложность
- **Лучший случай:** O(n log n)
- **Средний случай:** O(n log n)
- **Худший случай:** O(n²)

---

## 7. Пирамидальная сортировка (Heap Sort)

### Описание
Алгоритм строит бинарную кучу из массива, затем извлекает максимальные элементы, помещая их в конец массива.

### Код на C++
```cpp
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

void heapSort(vector<int>& arr) {
    int n = arr.size();
    
    for (int i = n / 2 - 1; i >= 0; i--)
        heapify(arr, n, i);
    
    for (int i = n - 1; i > 0; i--) {
        swap(arr[0], arr[i]);
        heapify(arr, i, 0);
    }
}
```

### Временная сложность
- **Все случаи:** O(n log n)

---

## 8. Линейный поиск (Linear Search)

### Описание
Простой алгоритм поиска, который последовательно проверяет каждый элемент массива.

### Код на C++
```cpp
int linearSearch(const vector<int>& arr, int target) {
    for (int i = 0; i < arr.size(); i++) {
        if (arr[i] == target) {
            return i;
        }
    }
    return -1;
}
```

### Временная сложность
- **Все случаи:** O(n)

---

## 9. Бинарный поиск (Binary Search)

### Описание
Эффективный алгоритм поиска в отсортированном массиве, который делит область поиска пополам на каждом шаге.

### Код на C++
```cpp
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
```

### Временная сложность
- **Все случаи:** O(log n)

---

## 10. Интерполяционный поиск (Interpolation Search)

### Описание
Улучшенная версия бинарного поиска, которая предсказывает позицию искомого элемента на основе значений.

### Код на C++
```cpp
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
```

### Временная сложность
- **Лучший случай:** O(log log n)
- **Худший случай:** O(n)

---

## 11. Поиск Фибоначчи (Fibonacci Search)

### Описание
Алгоритм поиска, использующий числа Фибоначчи для определения позиций сравнения.

### Код на C++
```cpp
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
```

### Временная сложность
- **Все случаи:** O(log n)

---

## Сводная таблица сложности алгоритмов

| Алгоритм | Лучший случай | Средний случай | Худший случай | Память |
|----------|---------------|----------------|---------------|---------|
| **Сортировка выбором** | O(n²) | O(n²) | O(n²) | O(1) |
| **Сортировка пузырьком** | O(n) | O(n²) | O(n²) | O(1) |
| **Сортировка вставками** | O(n) | O(n²) | O(n²) | O(1) |
| **Сортировка слиянием** | O(n log n) | O(n log n) | O(n log n) | O(n) |
| **Сортировка Шелла** | O(n log n) | O(n^(3/2)) | O(n²) | O(1) |
| **Быстрая сортировка** | O(n log n) | O(n log n) | O(n²) | O(log n) |
| **Пирамидальная сортировка** | O(n log n) | O(n log n) | O(n log n) | O(1) |
| **Линейный поиск** | O(1) | O(n) | O(n) | O(1) |
| **Бинарный поиск** | O(1) | O(log n) | O(log n) | O(1) |
| **Интерполяционный поиск** | O(1) | O(log log n) | O(n) | O(1) |
| **Поиск Фибоначчи** | O(1) | O(log n) | O(log n) | O(1) |
