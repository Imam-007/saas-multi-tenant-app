package com.saas.services;

import java.util.List;

public interface BasicService<Input, Output> {

    void create(final Input request);

    void update(final String id, final Input request);

    Output findById(final String id);

    List<Output> findAll();

    void delete(final String id);
}
