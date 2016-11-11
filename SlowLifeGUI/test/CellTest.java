/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Font;
import java.awt.event.ActionListener;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Carol
 */
public class CellTest {
    
    private Cell cell;
    @Before
    public void setUp() throws Exception{
       //initialize cell
        cell = new Cell();
       
    }
    
       /**
        * Test The font setting should be same as Font("Courier", Font.PLAIN, 12)
        */
	@Test
	public void testCellFont() {

            cell.getActionListeners().toString();
            Font font = cell.getFont();
            Font fontExp = new Font("Courier", Font.PLAIN, 12);

            assertEquals(fontExp, font);
	}
        
        /**
         * Test The ActionListener should be set successfully as CellButtonListener
         */
	@Test
	public void testCellListener() {

            ActionListener listener = cell.getActionListeners()[0];
            String listenerString = listener.toString();

            assertTrue(listenerString.contains("CellButtonListener"));
	}
        
                
        /**
         * Test toString() should return the correct string
         */
	@Test
	public void testToString0() {
            assertEquals(cell.toString(), ".");
	}
        
        /**
         * Test toString() should return the correct string
         */
	@Test
	public void testToString1() {
            cell.setAlive(true);
            assertEquals(cell.toString(), "X");
	}
        
        /**
         * Test toString() should return the correct string
         */
	@Test
	public void testToString2() {
            cell.setAlive(false);
            assertEquals(cell.toString(), ".");
	}

    
}
