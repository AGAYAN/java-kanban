package com.yandex.practicum.service;

import com.yandex.practicum.models.Node;
import com.yandex.practicum.models.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final Map<Integer, Node> history = new HashMap();
    private Node head;
    private Node tail;

    private Node addLink(Task task) {

        if (head == null) {
            head = new Node(null, task, null);
            return head;
        } else if (tail == null) {
            tail = new Node(head, task, null);
            head.setNext(tail);
            return tail;
        } else {
            final Node oldTail = tail;
            Node node = new Node(oldTail, task, null);
            oldTail.setNext(node);
            tail = node;
            return node;
        }
    }


    private void removeNode(Node node) {
        if (node == null) return;
        if (node == head) {
            head = node.getNext();
        }
        if (node == tail) {
            tail = node.getPrev();
        }
        if (node.getPrev()!= null) {
            node.getPrev().setNext(node.getNext());
        }
        if (node.getNext()!= null) {
            node.getNext().setPrev(node.getPrev());
        }
    }

    @Override
    public void add(Task task) {

        if (history.containsKey(task.getId())) {
            removeNode(history.get(task.getId()));
            history.remove(task.getId());
        }
        history.put(task.getId(),addLink(task));
    }

    @Override
    public void remove(int id) {

        Node node = history.get(id);

        history.remove(id);

        removeNode(node);
    }

    @Override
    public List<Task> getHistory() {
        List<Task> tempHistory = new ArrayList<>();
        Node node = head;
        while (node != null) {
            tempHistory.add(node.getData());
            node = node.getNext();
        }
        return tempHistory;
    }
}
