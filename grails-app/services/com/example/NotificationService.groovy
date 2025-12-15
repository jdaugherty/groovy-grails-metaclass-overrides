package com.example

class NotificationService {

    void sendNotification(@DelegatesTo(NotificationService) Closure c) {
        c.delegate = this
        c.call()
    }

    void setBookName(String name) {
        // ignored
    }
}
