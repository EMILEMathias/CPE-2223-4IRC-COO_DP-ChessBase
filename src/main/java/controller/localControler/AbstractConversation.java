package controller.localControler;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

public abstract class AbstractConversation<C extends Command, S> implements Conversation<C> {
    protected final Stack<S> undos, redos;

    public AbstractConversation() {
        this.undos= new Stack<S>();
        this.redos= new Stack<S>();
    }
}

class Stack<T> {

    //Delegate to avoid exposing too many Deque methods
    private final Deque<T> stack = new LinkedList<>();

    /**
     * @return null if stack is empty
     */
    public T pop() {
        return stack.pollLast(); //Not using pop since it throws NoSuchElementException if the deque is empty
    }

    public void push(T obj) {
        stack.addLast(obj);
    }

    public void clear() {
        stack.clear();
    }

    public void forEachFifo(Consumer<? super T> action) {
        stack.stream().forEachOrdered(action);
    }
}
