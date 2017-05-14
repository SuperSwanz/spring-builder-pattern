package com.greyseal.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;

@Controller
public abstract class BaseController<T, ID extends Serializable> {

}
