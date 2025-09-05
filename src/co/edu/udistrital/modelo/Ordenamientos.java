package co.edu.udistrital.modelo;

public class Ordenamientos {

    // ==== Burbuja ====
    public static void burbujaIndices(int[] arr, int[] indices, Metrica metric) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                metric.comparaciones++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    int tempIdx = indices[j];
                    indices[j] = indices[j + 1];
                    indices[j + 1] = tempIdx;
                    metric.intercambios++;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    // ==== Inserción ====
    public static void insercionIndices(int[] arr, int[] indices, Metrica metric) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i], keyIdx = indices[i];
            int j = i - 1;
            while (j >= 0) {
                metric.comparaciones++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    indices[j + 1] = indices[j];
                    metric.intercambios++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            indices[j + 1] = keyIdx;
        }
    }

    // ==== Selección ====
    public static void seleccionIndices(int[] arr, int[] indices, Metrica metric) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                metric.comparaciones++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                int tempIdx = indices[i];
                indices[i] = indices[minIdx];
                indices[minIdx] = tempIdx;
                metric.intercambios++;
            }
        }
    }

    // ==== MergeSort ====
    public static void mergeSortIndices(int[] arr, int[] indices, Metrica metric) {
        mergeSort(arr, indices, 0, arr.length - 1, metric);
    }

    private static void mergeSort(int[] arr, int[] indices, int left, int right, Metrica metric) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, indices, left, mid, metric);
            mergeSort(arr, indices, mid + 1, right, metric);
            merge(arr, indices, left, mid, right, metric);
        }
    }

    private static void merge(int[] arr, int[] indices, int left, int mid, int right, Metrica metric) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] Lidx = new int[n1];
        int[] R = new int[n2];
        int[] Ridx = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
            Lidx[i] = indices[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
            Ridx[j] = indices[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            metric.comparaciones++;
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                indices[k] = Lidx[i];
                i++;
            } else {
                arr[k] = R[j];
                indices[k] = Ridx[j];
                j++;
                metric.intercambios++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            indices[k] = Lidx[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            indices[k] = Ridx[j];
            j++;
            k++;
        }
    }

    // ==== QuickSort ====
    public static void quickSortIndices(int[] arr, int[] indices, Metrica metric) {
        quickSort(arr, indices, 0, arr.length - 1, metric);
    }

    private static void quickSort(int[] arr, int[] indices, int low, int high, Metrica metric) {
        if (low < high) {
            int pi = partition(arr, indices, low, high, metric);
            quickSort(arr, indices, low, pi - 1, metric);
            quickSort(arr, indices, pi + 1, high, metric);
        }
    }

    private static int partition(int[] arr, int[] indices, int low, int high, Metrica metric) {
        int pivot = arr[high];
        int pivotIdx = indices[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            metric.comparaciones++;
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                int tempIdx = indices[i];
                indices[i] = indices[j];
                indices[j] = tempIdx;
                metric.intercambios++;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        int tempIdx = indices[i + 1];
        indices[i + 1] = indices[high];
        indices[high] = tempIdx;
        metric.intercambios++;
        return i + 1;
    }
}
