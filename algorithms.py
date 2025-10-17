# Алгоритмы сортировки и поиска на Python

def selection_sort(arr):
    """Сортировка выбором - находит минимальный элемент и помещает его в начало"""
    n = len(arr)
    for i in range(n - 1):
        min_index = i
        for j in range(i + 1, n):
            if arr[j] < arr[min_index]:
                min_index = j
        arr[i], arr[min_index] = arr[min_index], arr[i]
    return arr

def bubble_sort(arr):
    """Сортировка пузырьком - последовательно сравнивает и обменивает соседние элементы"""
    n = len(arr)
    for i in range(n - 1):
        for j in range(n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    return arr

def insertion_sort(arr):
    """Сортировка вставками - вставляет каждый элемент в правильную позицию в отсортированной части"""
    for i in range(1, len(arr)):
        key = arr[i]
        j = i - 1
        while j >= 0 and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key
    return arr

def merge_sort(arr):
    """Сортировка слиянием - рекурсивно делит массив и объединяет отсортированные части"""
    if len(arr) <= 1:
        return arr
    
    def merge(left, right):
        result = []
        i = j = 0
        while i < len(left) and j < len(right):
            if left[i] <= right[j]:
                result.append(left[i])
                i += 1
            else:
                result.append(right[j])
                j += 1
        result.extend(left[i:])
        result.extend(right[j:])
        return result
    
    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    return merge(left, right)

def shell_sort(arr):
    """Сортировка Шелла - улучшенная сортировка вставками с уменьшающимся шагом"""
    n = len(arr)
    gap = n // 2
    while gap > 0:
        for i in range(gap, n):
            temp = arr[i]
            j = i
            while j >= gap and arr[j - gap] > temp:
                arr[j] = arr[j - gap]
                j -= gap
            arr[j] = temp
        gap //= 2
    return arr

def quick_sort(arr):
    """Быстрая сортировка - выбирает опорный элемент и рекурсивно сортирует части"""
    if len(arr) <= 1:
        return arr
    
    def partition(array, low, high):
        pivot = array[high]
        i = low - 1
        for j in range(low, high):
            if array[j] <= pivot:
                i += 1
                array[i], array[j] = array[j], array[i]
        array[i + 1], array[high] = array[high], array[i + 1]
        return i + 1
    
    def quick_sort_helper(array, low, high):
        if low < high:
            pi = partition(array, low, high)
            quick_sort_helper(array, low, pi - 1)
            quick_sort_helper(array, pi + 1, high)
    
    quick_sort_helper(arr, 0, len(arr) - 1)
    return arr

def heap_sort(arr):
    """Пирамидальная сортировка - строит кучу и извлекает максимальные элементы"""
    def heapify(array, n, i):
        largest = i
        left = 2 * i + 1
        right = 2 * i + 2
        
        if left < n and array[left] > array[largest]:
            largest = left
            
        if right < n and array[right] > array[largest]:
            largest = right
            
        if largest != i:
            array[i], array[largest] = array[largest], array[i]
            heapify(array, n, largest)
    
    n = len(arr)
    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i)
    
    for i in range(n - 1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]
        heapify(arr, i, 0)
    
    return arr

def linear_search(arr, target):
    """Линейный поиск - последовательно проверяет каждый элемент"""
    for i in range(len(arr)):
        if arr[i] == target:
            return i
    return -1

def binary_search(arr, target):
    """Бинарный поиск - работает только с отсортированными массивами"""
    left, right = 0, len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1

def interpolation_search(arr, target):
    """Интерполяционный поиск - предсказывает позицию элемента"""
    low, high = 0, len(arr) - 1
    
    while low <= high and target >= arr[low] and target <= arr[high]:
        if low == high:
            if arr[low] == target:
                return low
            return -1
        
        pos = low + ((high - low) // (arr[high] - arr[low])) * (target - arr[low])
        
        if arr[pos] == target:
            return pos
        elif arr[pos] < target:
            low = pos + 1
        else:
            high = pos - 1
    
    return -1

def fibonacci_search(arr, target):
    """Поиск Фибоначчи - использует числа Фибоначчи для определения позиций"""
    n = len(arr)
    fib_m2 = 0
    fib_m1 = 1
    fib_m = fib_m2 + fib_m1
    
    while fib_m < n:
        fib_m2 = fib_m1
        fib_m1 = fib_m
        fib_m = fib_m2 + fib_m1
    
    offset = -1
    
    while fib_m > 1:
        i = min(offset + fib_m2, n - 1)
        
        if arr[i] < target:
            fib_m = fib_m1
            fib_m1 = fib_m2
            fib_m2 = fib_m - fib_m1
            offset = i
        elif arr[i] > target:
            fib_m = fib_m2
            fib_m1 = fib_m1 - fib_m2
            fib_m2 = fib_m - fib_m1
        else:
            return i
    
    if fib_m1 and offset + 1 < n and arr[offset + 1] == target:
        return offset + 1
    
    return -1

# Демонстрация работы алгоритмов
if __name__ == "__main__":
    # Тестовые данные
    test_array = [64, 34, 25, 12, 22, 11, 90]
    sorted_array = [11, 12, 22, 25, 34, 64, 90]
    search_target = 25
    
    print("Исходный массив:", test_array)
    print("Отсортированный массив:", sorted_array)
    print("Цель поиска:", search_target)
    print()
    
    # Демонстрация сортировок
    print("=== АЛГОРИТМЫ СОРТИРОВКИ ===")
    
    arr1 = test_array.copy()
    print(f"Сортировка выбором: {selection_sort(arr1)}")
    
    arr2 = test_array.copy()
    print(f"Сортировка пузырьком: {bubble_sort(arr2)}")
    
    arr3 = test_array.copy()
    print(f"Сортировка вставками: {insertion_sort(arr3)}")
    
    arr4 = test_array.copy()
    print(f"Сортировка слиянием: {merge_sort(arr4)}")
    
    arr5 = test_array.copy()
    print(f"Сортировка Шелла: {shell_sort(arr5)}")
    
    arr6 = test_array.copy()
    print(f"Быстрая сортировка: {quick_sort(arr6)}")
    
    arr7 = test_array.copy()
    print(f"Пирамидальная сортировка: {heap_sort(arr7)}")
    
    print("\n=== АЛГОРИТМЫ ПОИСКА ===")
    
    print(f"Линейный поиск {search_target}: {linear_search(sorted_array, search_target)}")
    print(f"Бинарный поиск {search_target}: {binary_search(sorted_array, search_target)}")
    print(f"Интерполяционный поиск {search_target}: {interpolation_search(sorted_array, search_target)}")
    print(f"Поиск Фибоначчи {search_target}: {fibonacci_search(sorted_array, search_target)}")
