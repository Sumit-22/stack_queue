         public class CustomStack {
         protected int[] data;
         private static final int DEFAULT_SIZE = 10;

         int ptr = -1;

         public CustomStack() {
             this(DEFAULT_SIZE);
         }

         public CustomStack(int size) {
             this.data = new int[size];
         }

         public boolean push(int item) {
             if (isFull()) {
                 System.out.println("Stack is full!!");
                 return false;
             }
             ptr++;
             data[ptr] = item;
             return true;
         }

         public int pop() throws StackException {
             if (isEmpty()) {
                 throw new StackException("Cannot pop from an empty stack!!");
             }
//        int removed = data[ptr];
//        ptr--;
//        return removed;
             return data[ptr--];
         }

         public int peek() throws StackException {
             if (isEmpty()) {
                 throw new StackException("Cannot peek from an empty stack!!");
             }
             return data[ptr];
         }

         public boolean isFull() {
             return ptr == data.length - 1; // ptr is at last index
         }

         public boolean isEmpty() {
             return ptr == -1;
         }
     }


     //stack exception

    class StackException extends Exception {

         public StackException(String message) {
             super(message);
         }
     }

     //Dynamic Stack

    class DynamicStack extends CustomStack {

         public DynamicStack() {
             super(); // it will call CustomStack()
         }

         public DynamicStack(int size) {
             super(size); // it will call CustomStack(int size)
         }

         @Override
         public boolean push(int item) {
             // this takes care of it being full
             if (this.isFull()) {
                 // double the array size
                 int[] temp = new int[data.length * 2];

                 // copy all previous items in new data
                 for (int i = 0; i < data.length; i++) {
                     temp[i] = data[i];
                 }

                 data = temp;
             }

             // at this point we know that array is not full
             // insert item
             return super.push(item);
         }
     }

     class StackMain {
         public static void main(String[] args) throws StackException {
             CustomStack stack = new DynamicStack(5);

             stack.push(34);
             stack.push(45);
             stack.push(2);
             stack.push(9);
             stack.push(18);
             stack.push(89);


             System.out.println(stack.pop());
             System.out.println(stack.pop());
             System.out.println(stack.pop());
             System.out.println(stack.pop());
             System.out.println(stack.pop());
             System.out.println(stack.pop());
         }
     }

