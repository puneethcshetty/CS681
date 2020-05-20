package edu.umb.cs681.hw15;
@FunctionalInterface
public interface Observer {
void update(Observable observable,Object object);
}
