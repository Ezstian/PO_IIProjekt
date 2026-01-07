import javax.swing.*;

public class Welcome extends JFrame {


    private JPanel Panel1;
    private JLabel lab2;
    private JProgressBar progressBar1;
    private JLabel lblPlsWait;

    public Welcome(){
        super("lab02");
        this.setContentPane(this.Panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 400, height = 300;
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        progression();
        //otwarcie login form

    }
    private void progression(){
        int counter = 0;
        while (counter<=100){
            lblPlsWait.setText("Prosze czekać...");
            progressBar1.setValue(counter);
            try{
                Thread.sleep(100);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            counter+=5;
        }

        RadioTask radioTask = new RadioTask();
        //LoginForm loginForm = new LoginForm();
    }
}
