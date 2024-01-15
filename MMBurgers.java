class Heaps {
    public Events[] E;
    int size;
    int capacity;

    public Heaps() {
        E = new Events[2];
        size = 1;
        capacity = 2;
    }

    public void push(Events e1) {
        if (size == capacity) {
            int i = 1;
            Events[] E1 = new Events[2 * capacity];
            for (i = 1; i < size; i++) {
                E1[i] = E[i];
            }
            E = E1;
            capacity *= 2;
        }
        E[size] = e1;
        int i = size;
        while (i > 1) {
            if (E[size / 2].Eventcalltime > E[size].Eventcalltime) {
                Events temp;
                temp = E[size / 2];
                E[size / 2] = E[size];
                E[size] = temp;
                i = i / 2;
            } else if (E[size / 2].Eventcalltime == E[size].Eventcalltime) {
                if (E[size / 2].EventId > E[size].EventId) {
                    Events temp;
                    temp = E[size / 2];
                    E[size / 2] = E[size];
                    E[size] = temp;
                    i = i / 2;
                } else
                    break;
            } else
                break;
        }
        size++;
    }

    public Events pop() {
        Events temp;
        temp = E[size - 1];
        E[size - 1] = E[1];
        E[1] = temp;
        int i = 1;
        while (i < size) {
            Events min;
            int j;
            if (2 * i + 1 < size - 1) {
                if (E[2 * i + 1].Eventcalltime > E[2 * i].Eventcalltime) {
                    j = 2 * i;
                    min = E[2 * i];
                } else if (E[2 * i + 1].Eventcalltime < E[2 * i].Eventcalltime) {
                    j = 2 * i + 1;
                    min = E[2 * i + 1];
                } else {
                    if (E[2 * i].EventId < E[2 * i + 1].EventId) {
                        j = 2 * i;
                        min = E[2 * i];
                    } else {
                        j = 2 * i + 1;
                        min = E[2 * i + 1];
                    }
                }
            } else if (2 * i + 1 == size - 1) {
                j = 2 * i;
                min = E[2 * i];
            } else {
                break;
            }
            if (E[i].Eventcalltime > min.Eventcalltime) {
                temp = E[i];
                E[i] = min;
                E[j] = temp;
                i = j;
            } else if (E[i].Eventcalltime == min.Eventcalltime) {
                if (E[i].EventId > min.EventId) {
                    temp = E[i];
                    E[i] = min;
                    E[j] = temp;
                    i = j;
                } else
                    break;
            } else
                break;
        }
        temp = E[size - 1];
        size--;
        return temp;
    }

    public boolean isEmpty() {
        return size == 1;
    }
    public Events top() {
        return E[1];
    }
}

class Events {
    public int EventId;
    public int Eventcalltime;
    public customer c1;
    public burger b1;

    public Events(int id, int time, customer c) {
        EventId = id;
        Eventcalltime = time;
        c1 = c;
    }
}

class customer {
    public int id;
    public int order;
    public int arrival;
    public int customer_waitinqueue;
    public queue q;
    public int ordercompleted;
    public int totaltime;

    public customer(int id, int order, int arrival) {
        this.id = id;
        this.order = order;
        this.arrival = arrival;
        ordercompleted = 0;
    }

}

class queue {
    customer[] c;
    int first;
    int next;
    int capacity;
    int size;
    int queue_id;

    public queue(int id) {
        c = new customer[1];
        first = -1;
        next = 0;
        size = 0;
        capacity = 1;
        queue_id = id;
    }

    public void push(customer c1) {
        if (capacity == size) {
            customer[] c2 = new customer[2 * capacity];
            int j = 0;
            for (int i = first; i < capacity; i++) {
                c2[j] = c[i];
                j++;
            }
            for (int i = 0; i < first; i++) {
                c2[j] = c[i];
                j++;
            }
            c = c2;
            first = 0;
            next = capacity;
            capacity *= 2;
        }
        c[next] = c1;
        next = (next + 1) % capacity;
        if (first == -1)
            first = 0;
        size++;
    }

    boolean isEmpty() {
        return size == 0;

    }

    int sizeof() {
        return size;
    }

    customer front() {
        if (isEmpty()) {
            customer c1 = new customer(0, 0, 0);
            return c1;
        }
        return c[first];
    }

    customer pop() {
        if (isEmpty()) {
            customer c1 = new customer(0, 0, 0);
            return c1;
        } else {
            customer c1 = c[first];
            first = (first + 1) % capacity;
            size--;
            if (size == 0) {
                first = -1;
                next = 0;
            }
            return c1;
        }
    }
}

class heapforqueue {
    queue[] q2;
    int size;
    int capacity;

    public heapforqueue() {
        q2 = new queue[2];
        size = 1;
        capacity = 2;
    }

    public void push(queue q1) {
        if (size == capacity) {
            queue[] q3 = new queue[2 * capacity];
            int i = 1;
            for (i = 1; i < size; i++) {
                q3[i] = q2[i];
            }
            capacity *= 2;
            q2 = q3;
        }
        q2[size] = q1;
        int i = size;
        while (i > 1) {
            if (q2[i].size < q2[i / 2].size) {
                queue temp;
                temp = q2[i];
                q2[i] = q2[i / 2];
                q2[i / 2] = temp;
                i = i / 2;
            } else if (q2[i].size == q2[i / 2].size) {
                if (q2[i].queue_id < q2[i / 2].queue_id) {
                    queue temp;
                    temp = q2[i];
                    q2[i] = q2[i / 2];
                    q2[i / 2] = temp;
                    i = i / 2;
                } else
                    break;
            } else
                break;
        }
        size++;
    }

    public queue pop() {
        queue temp;
        temp = q2[1];
        q2[1] = q2[size - 1];
        q2[size - 1] = temp;
        int i = 1;
        while (i < size) {
            queue min;
            int j;
            if (2 * i + 1 < size - 1) {
                if (q2[2 * i + 1].size > q2[2 * i].size) {
                    j = 2 * i;
                    min = q2[2 * i];
                } else if (q2[2 * i + 1].size < q2[2 * i].size) {
                    j = 2 * i + 1;
                    min = q2[2 * i + 1];
                } else {
                    if (q2[2 * i].queue_id < q2[2 * i + 1].queue_id) {
                        j = 2 * i;
                        min = q2[2 * i];
                    } else {
                        j = 2 * i + 1;
                        min = q2[2 * i + 1];
                    }
                }
            } else if (2 * i + 1 == size - 1) {
                j = 2 * i;
                min = q2[2 * i];
            } else {
                break;
            }
            if (q2[i].size > min.size) {
                temp = q2[i];
                q2[i] = min;
                q2[j] = temp;
                i = j;
            } else if (q2[i].size == min.size) {
                if (q2[i].queue_id > min.queue_id) {
                    temp = q2[i];
                    q2[i] = min;
                    q2[j] = temp;
                    i = j;
                } else
                    break;
            } else
                break;
        }
        temp = q2[size - 1];
        size--;
        return temp;
    }
    public void correct_heap(queue q)
    {
        int i;
        for(i=1;i<size;i++)
        {
            if(q2[i]==q)
            {
                break;
            }
        }
        while(i>1)
        {
            if(q2[i].size<q2[i/2].size)
            {
                queue temp=q2[i];
                q2[i]=q2[i/2];
                q2[i/2]=temp;
                i=i/2;
            }
            else if(q2[i].size==q2[i/2].size)
            {
                if(q2[i].queue_id<q2[i/2].queue_id)
                {
                    queue temp=q2[i];
                    q2[i]=q2[i/2];
                    q2[i/2]=temp;
                    i=i/2;
                }
                else break;
            }
            else break;
        }
    }
}

class burger {
    public int burger_no;
    public int start_time;
    public int end_time;
    public customer c;

    burger(int burger_no, customer c) {
        this.burger_no = burger_no;
        this.c = c;
    }
}

class griddlewaitingqueue {
    burger[] b1;
    int first;
    int next;
    int capacity;
    int size;

    griddlewaitingqueue() {
        b1 = new burger[1];
        first = -1;
        next = 0;
        size = 0;
        capacity = 1;
    }

    public void push(burger b) {
        if (size == capacity) {
            burger[] b2 = new burger[2 * capacity];
            int k = 0;
            for (int i = first; i < capacity; i++) {
                b2[k] = b1[i];
                k++;
            }
            for (int i = 0; i < first; i++) {
                b2[k] = b1[i];
                k++;
            }
            b1 = b2;
            first = 0;
            next = capacity;
            capacity *= 2;
        }
        b1[next] = b;
        next = (next + 1) % capacity;
        if (first == -1)
            first = 0;
        size++;
    }

    public burger pop() {
        burger b = b1[first];
        first = (first + 1) % capacity;
        size--;
        if (size == 0) {
            first = -1;
            next = 0;
        }
        return b;
    }
}

class customer_array {
    public customer[] cust;
    public int size;
    public int capacity;

    customer_array() {
        cust = new customer[1];
        size = 0;
        capacity = 1;
    }

    public void push(customer c) {
        if (size == capacity) {
            int i = 0;
            customer[] cust1 = new customer[2 * capacity];
            for (i = 0; i < size; i++) {
                cust1[i] = cust[i];
            }
            cust = cust1;
            capacity *= 2;
        }
        cust[size] = c;
        size++;

    }
}

public class MMBurgers implements MMBurgersInterface {
    int K = 0, M = 0, T = 0;
    customer_array cust1 = new customer_array();
    Heaps H = new Heaps();
    griddlewaitingqueue wait_line = new griddlewaitingqueue();
    griddlewaitingqueue griddle = new griddlewaitingqueue();
    heapforqueue H1 = new heapforqueue();

    public void Exit_billingline(customer c, int time) {
        c.q.pop();
        H1.correct_heap(c.q);
        for (int i = 0; i < c.order; i++) {
            burger b1 = new burger(i + 1, c);
            if (griddle.size != M) {
                griddle.push(b1);
                b1.start_time = time;
                b1.end_time = time + 10;
                Events E = new Events(2, time + 10, c);
                H.push(E);
            } else {
                wait_line.push(b1);
            }
        }
    }

    public void Exit_griddle() {
        burger b1 = griddle.pop();
        if (wait_line.size != 0) {
            burger b2 = wait_line.pop();
            griddle.push(b2);
            b2.start_time = b1.end_time;
            b2.end_time = b2.start_time + 10;
            Events E = new Events(2, b2.end_time, b2.c);
            H.push(E);
        }
        b1.c.ordercompleted++;
        if (b1.c.ordercompleted == b1.c.order) {
            Events E= new Events(3,b1.end_time+1, b1.c);
            H.push(E);
        }
    }
    public void Exit_restaurant(customer c,int time){
        c.totaltime=time;
    }
    public boolean isEmpty() {
        // your implementation
        // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        return H.isEmpty();
    }

    public void setK(int k) throws IllegalNumberException {
        // your implementation
        // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        if(K!=0)
        throw new IllegalNumberException("E");
        if (k <= 0) {
            throw new IllegalNumberException("E");
        }
        K = k;
        queue[] q1 = new queue[k];
        for (int i = 0; i < k; i++) {
            q1[i] = new queue(i + 1);
            H1.push(q1[i]);
        }
    }

    public void setM(int m) throws IllegalNumberException {
        // your implementation
        // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        if(M!=0)
        {
            throw new IllegalNumberException("E");
        }
        if (m <= 0) {
            throw new IllegalNumberException("E");
        }
        M = m;
    }

    public void advanceTime(int t) throws IllegalNumberException {
        // your implementation
        // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        if (T > t)
            throw new IllegalNumberException("E");
        else
            T = t;
        if (H.isEmpty())
            return;
        while (!H.isEmpty()&&H.top().Eventcalltime <= t) {
            Events E1 = H.pop();
            if (E1.EventId == 1) {
                Exit_billingline(E1.c1, E1.Eventcalltime);
            } else if (E1.EventId==2) {
                Exit_griddle();
            } else{
                Exit_restaurant(E1.c1,E1.Eventcalltime);
            }
        }
    }

    public void arriveCustomer(int id, int t, int numb) throws IllegalNumberException {
        // your implementation
        // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        try {
            advanceTime(t);
        } catch (IllegalNumberException E) {
            throw new IllegalNumberException("E");
        }
        if(id-1>cust1.size)
        {
            throw new IllegalNumberException("E");
        }
        customer c = new customer(id, numb, t);
        cust1.push(c);
        queue q1 = H1.pop();
        c.q = q1;
        q1.push(c);
        H1.push(q1);
        int i = q1.queue_id;
        int size = q1.size;
        int t1 = (size) * i;
        c.customer_waitinqueue = t + t1;
        Events E = new Events(1, t + t1, c);
        H.push(E);
    }

    public int customerState(int id, int t) throws IllegalNumberException {
        // your implementation
        // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        try {
            advanceTime(t);
        } catch (IllegalNumberException E) {
            throw new IllegalNumberException("e");
        }
        if (id - 1 >= cust1.size)
            return 0;
        customer c = cust1.cust[id - 1];
        if (c.totaltime != 0) {
            if (t == c.totaltime - 1) {
                return K + 1;
            } else
                return K + 2;
        } else {
            if (t < c.customer_waitinqueue)
                return c.q.queue_id;
            else
                return K + 1;
        }
    }

    public int griddleState(int t) throws IllegalNumberException {
        // your implementation
        // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        try {
            advanceTime(t);
        } catch (IllegalNumberException E) {
            throw new IllegalNumberException("E");
        }
        return griddle.size;
    }

    public int griddleWait(int t) throws IllegalNumberException {
        // your implementation
        // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        try {
            advanceTime(t);
        } catch (IllegalNumberException E) {
            throw new IllegalNumberException("E");
        }
        return wait_line.size;
    }

    public int customerWaitTime(int id) throws IllegalNumberException {
        // your implementation
        // throw new java.lang.UnsupportedOperationException("Not implemented yet.");

        if (id - 1 >= cust1.size)
            throw new IllegalNumberException("E");
        customer c = cust1.cust[id - 1];
        if (c.totaltime != 0) {
            return c.totaltime-c.arrival;
        } else
            throw new IllegalNumberException("E");
    }

    public float avgWaitTime() {
        // your implementation
        // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        int i;
        float sum = 0;
        for (i = 0; i < cust1.size; i++) {
            sum += (cust1.cust[i].totaltime-cust1.cust[i].arrival);
        }
        return sum / (cust1.size);
    }

}