public class CustomDequeue {
    private int [] data; // just to secure
    private static final int DEFAULT_SIZE = 10; // we can't change the size
         int front =0;
         int rear=0;
         int size =0;
         public CustomDequeue(){
             this(DEFAULT_SIZE);
         }
         public CustomDequeue(int size){
             this.data = new int[size];
         }
         public boolean isFull(){
             return size == data.length;
         }
         public boolean isEmpty(){
             return size==0;
         }
         public boolean insertEnd(int item){
             if(isFull()){
                 return false;
             }
             data[rear++] = item;
             rear =rear% data.length;
             size++;
             return true;
         }
         public boolean insertBeg(int item)  {
             if (isFull()) {
                 return false;
             }
                 front = (front-1+data.length) % data.length;
                 data[front] = item;

                 size++;
                 return true;
             }

         public int deleteBeg() throws Exception{
             if(isEmpty()) {
                 throw new Exception ("queue is empty");
             }
             int removed = data[front++];
             front =front% data.length;
             size--;
             return removed;
         }
         public int deleteEnd() throws Exception {
             if (isEmpty()){
                 throw new Exception("queue is empty");
             }
             rear=(data.length -1+rear) % data.length;
             int removed = data[rear];
             size--;
             return removed;
         }
    public void display() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
            return;
        }
        int i = front;
        //    while(i< rear){    --> this will give error.You know how to visualise so, check by visualise
        while(i!= rear){
            System.out.print(data[i] + " <- ");
            i =(i+1) % data.length;  // or  i = (data.length+1+i)%data.length
        }
        System.out.println(data[rear]);
        System.out.println();
    }
    public static void main(String[] args) {
        CustomDequeue deque = new CustomDequeue(5);

        // Insert elements at the beginning and end
        deque.insertBeg(1);
        deque.insertEnd(2);
        deque.insertBeg(3);
        deque.insertEnd(4);

        deque.display(); // Display the deque: 3 <- 1 <- 2 <- 4

        // Delete elements from the beginning and end
        try {
            int removedFront = deque.deleteBeg();
            System.out.println("Removed from the front: " + removedFront);
            int removedEnd = deque.deleteEnd();
            System.out.println("Removed from the end: " + removedEnd);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        deque.display(); // Display the updated deque: 1 <- 2

        // Insert one more element at the end
        deque.insertEnd(5);

        deque.display(); // Display the updated deque: 1 <- 2 <- 5
    }
}


/* output is :--
3 <- 1 <- 2 <- 4 <- 0

        Removed from the front: 3
        Removed from the end: 4
        1 <- 2 <- 4

        1 <- 2 <- 5 <- 0
*/