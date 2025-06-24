package me.lightha.lhc.api;

import me.lightha.lhc.gui.fake.FakeInventory;
import me.lightha.lhc.gui.fake.impl.FakeInventoryImpl;

public interface IFakeInventory {
    FakeInventory fakeInv = new FakeInventoryImpl();
}
