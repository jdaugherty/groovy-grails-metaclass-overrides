package com.example

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

@Integration
@Rollback
@ConfineMetaClassChanges([Book])
class BookSpec extends Specification {
    AuthorService authorService

    Book book

    private Author setupData() {
        book = new Book(name: "The Great Gatsby", genre: Genre.Comedy)
        book.save(flush: true, failOnError: true)

        Author author = new Author(name: "F. Scott Fitzgerald", favoriteBook: book)
        author.save(flush: true, failOnError: true)
    }

    def "test"() {
        given:
        Author author = setupData()

        and:
        author.favoriteBook.metaClass.isFavoriteBookGreat = { -> true }

        when:
        authorService.processFavoriteBook(book, false)

        then:
        def e = thrown(Exception)
        e.message == 'Favorite book is already great'
    }
}
