public class CustomQueue {
    // in simple queue ,front is for deletion and rear or end is for insertion -->>follows fifo principle
        private int[] data;

        private static final int DEFAULT_SIZE = 10;

        int rear = 0;

        public CustomQueue(){
            this(DEFAULT_SIZE);
        }

        public CustomQueue(int size) {
            this.data = new int[size];
        }

        public boolean isFull() {
            return rear == data.length; // ptr is at last index
        }

        public boolean isEmpty() {
            return rear == 0;
        }

        public boolean insert(int item) {
            if (isFull()) {
                return false;
            }
            data[rear++] = item;
            return true;
        }

        public int remove() throws Exception {
            if (isEmpty()) {
                throw new Exception("Queue is empty");
            }

            int removed = data[0];

            // shift the elements to left
            for (int i = 1; i < rear; i++) {
                data[i-1] = data[i];
            }
            rear--;
            return removed;
        }

        public int front() throws Exception{
            if (isEmpty()) {
                throw new Exception("Queue is empty");
            }
            return data[0];
        }

        public void display() {
            for (int i = 0; i < rear; i++) {
                System.out.print(data[i] + " <- ");
            }
            System.out.println("END");
        }

    }
    //circular queue :- or ring buffer :-
        class CircularQueue {
        protected int[] data;
        private static final int DEFAULT_SIZE = 10;

        protected int end = 0;
        protected int front = 0;
        private int size = 0;

        public CircularQueue(){
            this(DEFAULT_SIZE);
        }

        public CircularQueue(int size) {
            this.data = new int[size];
        }

        public boolean isFull() {
            return size == data.length; // ptr is at last index
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean insert(int item) {
            if (isFull()) {
                return false;
            }
            data[end++] = item;
            end = end % data.length;
            size++;
            return true;
        }

        public int remove() throws Exception {
            if (isEmpty()) {
                throw new Exception("Queue is empty");
            }

            int removed = data[front++];
            front = front % data.length;
            size--;
            return removed;
        }

        public int front() throws Exception{
            if (isEmpty()) {
                throw new Exception("Queue is empty");
            }
            return data[front];
        }

        public void display() {
            if (isEmpty()) {
                System.out.println("Empty");
                return;
            }
            int i = front;
            do {
                System.out.print(data[i] + " -> ");
                i++;
                i %= data.length;
            } while (i != end);
            System.out.println("END");
        }

    }
            // Dynamic queue

         class DynamicQueue extends CircularQueue{
        public DynamicQueue() {
            super();
        }

        public DynamicQueue(int size) {
            super(size);
        }

        @Override
        public boolean insert(int item) {

            // this takes care of it being full
            if (this.isFull()) {
                // double the array size
                int[] temp = new int[data.length * 2];

                // copy all previous items in new data
                for (int i = 0; i < data.length; i++) {
                    temp[i] = data[(front + i) % data.length];
                }
                front = 0;
                end = data.length;
                data = temp;
            }

            // at this point we know that array is not full
            // insert item
            return super.insert(item);
        }
    }

   class QueueMain {
        public static void main(String[] args) throws Exception {
            CircularQueue queue = new CircularQueue(5);
            queue.insert(3);
            queue.insert(6);
            queue.insert(5);
            queue.insert(19);
            queue.insert(1);

            queue.display();

            System.out.println(queue.remove());
            queue.insert(133);
            queue.display();

            System.out.println(queue.remove());
            queue.insert(99);
            queue.display();

        }
    }
