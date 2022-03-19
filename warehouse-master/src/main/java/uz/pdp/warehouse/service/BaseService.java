package uz.pdp.warehouse.service;

import uz.pdp.warehouse.payload.Result;

import java.util.List;

public interface BaseService<T, R> {
    Result add(T t);
    List<R> getAll();
    R getOne(Integer id);
    Result edit(Integer id, T t);
    Result delete(Integer id);
}
