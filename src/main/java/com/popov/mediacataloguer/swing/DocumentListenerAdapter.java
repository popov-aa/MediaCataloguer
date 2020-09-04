package com.popov.mediacataloguer.swing;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.function.Consumer;

public class DocumentListenerAdapter implements DocumentListener {
    Consumer<DocumentEvent> consumer;

    public DocumentListenerAdapter (Consumer<DocumentEvent> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        consumer.accept(documentEvent);
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        consumer.accept(documentEvent);
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        consumer.accept(documentEvent);
    }
}
