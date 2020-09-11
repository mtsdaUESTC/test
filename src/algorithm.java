public class algorithm {
    public static void selectionSort(int[] a){
        int len = a.length;
        int temp,minIndex;
        for(int i = 0;i<len-1;i++){
            minIndex = i;
            for(int j = i+1;j<len;j++){
                if(a[j]<a[minIndex]) minIndex = j;
            }
            temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
        }
    }
    public static void insertSort(int[] a){
        int n = a.length;
        for(int i = 1;i<n; i++){
            int value = a[i];
            int j = i-1;
            for(;j>=0;j--){
                if(value<a[j]){
                    a[j+1] = a[j];
                }else {
                    break;
                }
            }
            a[j+1] = value;
        }
    }

}
