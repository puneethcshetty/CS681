package edu.umb.cs681.hw17;
@FunctionalInterface
public interface Observer {
void update(Observable observable,Object object);
}
