package by.bookstore.service;

import by.bookstore.entity.Store;

public interface ModerService {
    void updateInWait(Store store);
    void updateInComplete(Store store);
}
