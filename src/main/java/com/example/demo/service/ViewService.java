package com.example.demo.service;

import com.example.demo.model.View;
import com.example.demo.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ViewService {

    @Autowired
    ViewRepository viewRepository;

    public Optional<Iterable<View>> getAllViews() {
        return Optional.of(viewRepository.findAll());
    }

    public Optional<View> createView(View View){
        return Optional.of(viewRepository.save(View));
    }

    public Optional<View> findViewById(Long id){
        return viewRepository.findById(id);
    }

    public Optional<View> deleteViewById(Long id){
        //Find out IF this id-View IS in our DB
        Optional<View> ViewFound = viewRepository.findById(id);
        if(ViewFound.isPresent()) {
            viewRepository.deleteById(id);
            return Optional.of(ViewFound.get());
        } else {
            return null;
        }
    }

    public Optional<View> updateView(View View) {
        Optional<View> ViewFound = viewRepository.findById(View.getViewId());
        if(ViewFound.isPresent()) {
            return Optional.of(viewRepository.save(View));
        } else {
            return null;
        }
    }

    public int count() {
        return (int) viewRepository.count();
    }

}