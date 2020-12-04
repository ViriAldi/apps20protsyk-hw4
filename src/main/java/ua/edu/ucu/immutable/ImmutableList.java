package ua.edu.ucu.immutable;

public interface ImmutableList<T> {

    ImmutableList add(T e);

    ImmutableList add(int index, T e);

    ImmutableList addAll(T[] c);

    ImmutableList addAll(int index, T[] c);

    Object get(int index);

    ImmutableList remove(int index);

    ImmutableList set(int index, T e);

    int indexOf(T e);

    int size();

    ImmutableList clear();

    boolean isEmpty();

    Object[] toArray();

    @Override
    String toString();
}
