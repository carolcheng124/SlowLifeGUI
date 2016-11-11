/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
public class MainPanelTest {
    MainPanel panel;
    public Cell[][] _cells;
    public boolean[][] _backupCells;
    public int SIZE = 15;

    
    @Before
    public void setUp() throws Exception{
       //initialize panel with size of 15
       panel = new MainPanel(SIZE);
       _cells = panel.getCells();
    }
    
    
    @After
    public void tearDown() throws Exception{
    }
    

    /**
     * Test of getNumNeighbors method, of class MainPanel.
     * test the removal of converToInt() won't harm getNumNeighbors()
     * set three neighbors around cell[0][0] alive
     * the number of neighbors for position (0, 0) should be 3
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    @Test
    public void TestGetNumNeighbors0() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        System.out.println("Test getNumNeighbors() method...");
        //legacy code testing
        Method method = MainPanel.class.getDeclaredMethod("getNumNeighbors", int.class, int.class);
        method.setAccessible(true);
        
        //set three neighbors alive
        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                _cells[i][j].setAlive(true);
            }
	}
        
        int res = (int)method.invoke(panel, 0, 0);
        int target = 3; 
        assertEquals(res, target); //assume the target cell should 
    }

    /**
     * Test of getNumNeighbors method, of class MainPanel.
     * test the removal of converToInt() won't harm getNumNeighbors()
     * all neighbors around cell[0][0] die
     * the number of neighbors for position (0, 0) should be 0
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    @Test
    public void TestGetNumNeighbors1() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        System.out.println("Test getNumNeighbors() method...");
        //legacy code testing
        Method method = MainPanel.class.getDeclaredMethod("getNumNeighbors", int.class, int.class);
        method.setAccessible(true);

        int res = (int)method.invoke(panel, 0, 0);
        int target = 0; 
        assertEquals(res, target); //assume the target cell should 
    }
    
    /**
     * Test of getNumNeighbors method, of class MainPanel.
     * test the removal of converToInt() won't harm getNumNeighbors()
     * all neighbors around cell[1][1] alive
     * the number of neighbors for position (1, 1) should be 8
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    @Test
    public void TestGetNumNeighbors2() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        System.out.println("Test getNumNeighbors() method...");
        //legacy code testing
        Method method = MainPanel.class.getDeclaredMethod("getNumNeighbors", int.class, int.class);
        method.setAccessible(true);
        
        //set all neighbors alive
        for(int i=0; i<SIZE; i++) {
            for(int j=0; j<SIZE; j++) {
                _cells[i][j].setAlive(true);
            }
	}
        
        int res = (int)method.invoke(panel, 1, 1);
        int target = 8; 
        assertEquals(res, target); //assume the target cell should 
    }
    
    /**
     * Test of backup method, of class MainPanel.
     * Test the modification to backup() won't change its function
     * Test no cell that alive, after running for one iteration, the backup will return panel to the status in last iteration
     */
    @Test
    public void TestBackup0(){
        //1.no cell is alive 
        
        //2.run and backup
        panel.run();
        
        //3.convert to backup
        panel.undo();
        
        //4.verify the current status pattern is the same as the one in last iteration
        for(int i=0; i<15; i++) {
            for(int j=0; j<15; j++) {
                    assertFalse(_cells[i][j].getAlive());
            }
	}
    
    }
    
    
   /**
     * Test of backup method, of class MainPanel.
     * Test the modification to backup() won't change its function
     * Test after running for one iteration, the backup will return panel to the status in last iteration
     */
    @Test
    public void TestBackup1(){
        //1.set original status pattern for cell[][]
        for(int i=0; i<15; i++) {
            for(int j=0; j<15; j++) {
                _cells[i][j] = new Cell();
                if(i <= SIZE/2 && j == SIZE/2 ||i == SIZE/2&& j <= SIZE/2 ){
                    _cells[i][j].setAlive(true);
                }else _cells[i][j].setAlive(false);
            }
	}
        
        //2.run and backup
        panel.run();
        
        //3.convert to backup
        panel.undo();
        
        //4.verify the current status pattern is the same as the one in last iteration
        for(int i=0; i<15; i++) {
            for(int j=0; j<15; j++) {
                if(i <= SIZE/2 && j == SIZE/2 || i == SIZE/2 && j <= SIZE/2 ){
                    assertTrue(_cells[i][j].getAlive());
                }else assertFalse(_cells[i][j].getAlive());
            }
	}
        
    
    }
    
       /**
     * Test of backup method, of class MainPanel.
     * Test the modification to backup() won't change its function
     * Test cells that all alive after running for one iteration, the backup will return panel to the status in last iteration
     */
    @Test
    public void TestBackup2(){
        //1.set all cells alive
        for(int i=0; i<15; i++) {
            for(int j=0; j<15; j++) {
                _cells[i][j] = new Cell();
                _cells[i][j].setAlive(true);
            }
	}
        
        //2.run and backup
        panel.run();
        
        //3.convert to backup
        panel.undo();
        
        //4.verify the current status pattern is the same as the one in last iteration
        for(int i=0; i<15; i++) {
            for(int j=0; j<15; j++) {
                    assertTrue(_cells[i][j].getAlive());
            }
	}
        
    }
    
    
    
    /**
     * Test of debugPrint method, of class MainPanel.
     * Test the modification to debugPrint() won't change its function
     * Test the debugPrint print the backup cells and current cells normally
     */
    @Test
    public void TestDebugPrint(){
        //use script to make record
        StringBuilder script = new StringBuilder();
        
        //1.set original status pattern for cell[][]
        //script records backup cell
        script.append("Backup cells\n");
        for(int i=0; i<15; i++) {
            for(int j=0; j<15; j++) {
                _cells[i][j] = new Cell();
                if(i <= SIZE/2 && j == SIZE/2 ||i == SIZE/2&& j <= SIZE/2 ){
                    _cells[i][j].setAlive(true);
                    script.append("X");
                }else{
                    _cells[i][j].setAlive(false);
                    script.append(".");
                }
            }
            script.append("\n");
	}
        
        //2.run and backup
        panel.run();
        
        //script records current cells
        script.append("Current cells:\n");
        for(int i=0; i<15; i++) {
            for(int j=0; j<15; j++) {
                if(_cells[i][j].getAlive())
                    script.append("X");
                else script.append(".");
            }
            script.append("\n");
	}
        
        //to record the console print
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        //3.debugPrint
        panel.debugPrint();
        
        //4.verify
        assertEquals(script.toString(), outContent.toString());

    }
    
    /**
     * Test of runContinuous method, of class MainPanel.
     * Manual test in readme
     */

    
    /**
     * Test of undo method, of class MainPanel.
     * boolean backupCell[][]
     * Confirm the removal of redundant convertToBoolean() won't change the function of undo()
     * Test panel should have the same status with backup after undo
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        
        //1.set original status pattern for cell[][]
        for(int i=0; i<15; i++) {
            for(int j=0; j<15; j++) {
                _cells[i][j] = new Cell();
                if(i <= SIZE/2 && j == SIZE/2 ||i == SIZE/2&& j <= SIZE/2 ){
                    _cells[i][j].setAlive(true);
                }else _cells[i][j].setAlive(false);
            }
	}
        
        //2.run one iteration and backup 
        panel.run();
        
        //3.fake backupCells, same as the original status pattern set for cell[][]
        _backupCells = new boolean[SIZE][SIZE];
        for(int i=0; i<SIZE; i++) {
            for(int j=0; j<SIZE; j++) {
                if(i <= SIZE/2 && j == SIZE/2 ||i == SIZE/2&& j <= SIZE/2 ){
                    _backupCells[i][j] = true;
                }else _backupCells[i][j] = false;
            }
	}
        
        //4. undo to backupcells
        panel.undo();
        
        //5.verify whether current status is the same with backup's
        for(int i=0; i<SIZE; i++) {
            for(int j=0; j<SIZE; j++) {
                    assertEquals(_cells[i][j].getAlive(), _backupCells[i][j]);
            }
	}
        
          
    }

    /**
     * Test of load method, of class MainPanel.
     * manually set some cells in _cells[][] alive
     * Confirm the modification to load() won't change its function
     * Test all cells' current status should be the same as backup file after load it back
     */
    @Test
    public void testLoad() {
        System.out.println("Test load");
        
        //1.set status for each cell
        for(int i=0; i<15; i++) {
            for(int j=0; j<15; j++) {
                _cells[i][j] = new Cell();
                if(i == j){
                    _cells[i][j].setAlive(true);
                }else _cells[i][j].setAlive(false);
            }
	}
        
        //2.write to string as backup file
        String file = panel.toString();
        String[] stringArr = file.split("\\n");
        ArrayList<String> lines = new ArrayList<>();
        for(String s: stringArr){
            lines.add(s);
        }
        
        //3.run one iteration to disturb the original pattern
        panel.run();
        
        //4.load back from backup file
        panel.load(lines);
        
        //5.verify
        for(int i=0; i<15; i++) {
            for(int j=0; j<15; j++) {
                if(i == j){
                    assertTrue(_cells[i][j].getAlive());
                }else assertFalse(_cells[i][j].getAlive());
            }
	}
        
    }
    
}
