/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import MainClass.Process;
import java.util.concurrent.Semaphore;


public class Queue {
    private Node head;
    private Node tail;
    private int size;
    private Semaphore semaphore;

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.semaphore = new Semaphore(1);
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void enqueue(Process proceso) {
        try {
            semaphore.acquire(); // Bloquea el acceso a la cola
            Node newNode = new Node(proceso);

            if (tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.setpNext(newNode);
                tail = newNode;
            }

            size++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Libera el acceso a la cola
        }
    }

    public Process dequeue() {
        try {
            semaphore.acquire(); // Bloquea el acceso a la cola
            if (head == null) {
                System.out.println("La cola está vacía.");
                return null;
            }

            Process proceso = (Process) head.getData();
            head = head.getpNext();

            if (head == null) {
                tail = null;
            }

            size--;
            return proceso;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            semaphore.release(); // Libera el acceso a la cola
        }
    }
}
