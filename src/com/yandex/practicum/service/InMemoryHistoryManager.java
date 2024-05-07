package com.yandex.practicum.service;

import com.yandex.practicum.models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    private final Map<Integer, Node> history = new HashMap<>();
    private Node head;
    private Node tail;

    class Node {
        private Task data;
        private Node next;
        private Node prev;

        public Node(Node prev, Task data, Node next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(Task task) {
        if (history.containsKey(task.getId())) {
            removeNode(history.get(task.getId()));
            history.remove(task.getId());
        }
        history.put(task.getId(), addLink(task));
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
            tempHistory.add(node.data);
            node = node.next;
        }
        return tempHistory;
    }

    private Node addLink(Task task) {
        if (head == null) {
            head = new Node(null, task, null);
            return head;
        } else if (tail == null) {
            tail = new Node(head, task, null);
            head.next = tail;
            return tail;
        } else {
            final Node oldTail = tail;
            Node node = new Node(oldTail, task, null);
            oldTail.next = node;
            tail = node;
            return node;
        }
    }

    private void removeNode(Node node) {
        final Node next = node.next;
        final Node prev = node.prev;

        if (node == head) {
            head = next;
        }
        if (node == tail) {
            tail = prev;
        }
        if (prev != null) {
            prev.next = node.next;
        }
        if (next != null) {
            next.prev = node.prev;
        }
    }
}
