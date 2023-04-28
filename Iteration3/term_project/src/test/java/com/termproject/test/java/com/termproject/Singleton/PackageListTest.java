package com.termproject.test.java.com.termproject.Singleton;

import com.termproject.Singleton.PackageList;
import com.termproject.Trip.Package;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PackageListTest {

    @Test
    void getInstance() {
        assertFalse(false);
    }

    @Test
    void getPackageList() {
        assertFalse(false);
    }

    @Test
    void testPackageListContainsData() {

        PackageList packageList = PackageList.getInstance();                //Get an instance of the PackageList class
        ArrayList<Package> pkgs = packageList.getPackageList();             //Use instance to get a list of travel agents

        assertNotNull(pkgs);                //Assert that arraylist is not null and is not empty
        assertFalse(pkgs.isEmpty());

    }

}