public class Trimargesort{
    void merge (int arr[],int left,int mid,int right){
        int l=mid-left+1;
        int r=right -mid;
        int leftArray[]=new int [l];
        int rightArray[]=new int[r];
        for(int i=0;i<l;++i){
            leftArray[i]=arr[left+i];

        }
for (int j=0;j<r;++j){
    rightArray[j]=arr[mid+1+j];

}
int i=0,j=0;
int k=left;
while(i<l&&j<r){
    if(leftArray[i] >= rightArray[j]){
        arr[k]=leftArray[i];
        i++;
    }else{arr[k]=rightArray[j];
        j++;
    }

    k++;

}
while(i<l){
    arr[k]=leftArray[i];
    i++;
    k++;
    

}
while(j<r){
    arr[k]=rightArray[j];
    j++;
k++;
}
    }
    void sort(int arr[],int left,int right){
        if (left<right){
            int mid =(left+right)/2;
            sort(arr,left,mid);
            sort(arr,mid+1,right);
            merge(arr, left, mid, right);
        }
    }
    public static void main(String[] args) {
        int arr[]={90,23,101,45,65,89,34,23};
        Trimargesort ob=new Trimargesort();
        ob.sort(arr,0,arr.length-1);
        System.out.println("sorted array");
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]+" ");
        }
    }
    
}