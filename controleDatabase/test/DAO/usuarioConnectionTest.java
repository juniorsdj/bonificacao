/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import PO.usuario;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author QueroDelivery
 */
public class usuarioConnectionTest {
    
    public usuarioConnectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllUsuarios method, of class usuarioConnection.
     */
    @Test
    public void testGetAllUsuarios() throws Exception {
        System.out.println("getAllUsuarios");
        usuarioConnection instance = new usuarioConnection();
        List expResult = null;
        List result = instance.getAllUsuarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuarioPorId method, of class usuarioConnection.
     */
    @Test
    public void testGetUsuarioPorId() throws Exception {
        System.out.println("getUsuarioPorId");
        int id = 0;
        usuarioConnection instance = new usuarioConnection();
        usuario expResult = null;
        usuario result = instance.getUsuarioPorId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateMicroFranq method, of class usuarioConnection.
     */
    @Test
    public void testUpdateMicroFranq() {
        System.out.println("updateMicroFranq");
        usuario usu = null;
        usuarioConnection instance = new usuarioConnection();
        instance.updateMicroFranq(usu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertUsuario method, of class usuarioConnection.
     */
    @Test
    public void testInsertUsuario() throws Exception {
        System.out.println("insertUsuario");
        usuario usuario = null;
        usuarioConnection instance = new usuarioConnection();
        int expResult = 0;
        int result = instance.insertUsuario(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
