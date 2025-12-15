package com.example

import groovy.transform.CompileStatic

@CompileStatic
class AuthorService {
    NotificationService notificationService

    Book processFavoriteBook(Book book, boolean shouldSkip = false) {
        if(!book) {
            throw new IllegalArgumentException("Book cannot be null")
        }

        if(!shouldSkip && book.favoriteBookGreat) {
            throw new IllegalStateException("Favorite book is already great")
        }

        notificationService.sendNotification {
            bookName = book.name
        }

        throw new UnsupportedOperationException("Not implemented yet")
    }
}
