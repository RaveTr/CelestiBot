package com.mememan.celestibot.api.reg;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;

import java.util.concurrent.BlockingDeque;
import java.util.function.Supplier;

public interface IRegistrar<T> {

    T registerElement(Supplier<T> elementSup);

    BlockingDeque<T> queuedRegistrations();

    Object2ObjectArrayMap<T, Throwable> failedRegistrations();
}
