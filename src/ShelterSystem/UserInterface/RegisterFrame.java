package ShelterSystem.UserInterface;


import ShelterSystem.Base.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
//注册界面

public class RegisterFrame extends JFrame {

    private JLabel username_Label;    //用户名标签。
    private JLabel password_Label;    //密码标签。
    private JLabel repassword_Label;    //确认密码标签。
    private JTextField username_Text;    //用户名文本域。
    private JPasswordField password_Text;    //密码文本域。
    private JPasswordField repassword_Text;    //确认密码文本域。
    private JButton register_Button;    //注册按钮。
    private JButton cancel_Button;    //取消按钮。
    private JFrame jf;    //当前窗口 。

    public RegisterFrame() {
        super("动物收容所用户注册界面");
        this.jf = this;
        this.setLayout(null);//设置为空布局。
        this.setSize(400, 300);//设置大小。
        Container c = this.getContentPane();
        c.setBackground(Color.WHITE);//设置背景颜色。
        username_Label = new JLabel("用户名:");    //创建"用户名"标签。
        username_Label.setBounds(100, 60, 50, 20);    //设置"用户名"标签位置。
        c.add(username_Label);    //添加"用户名"标签。

        username_Text = new JTextField();    //创建"用户名"文本域。
        username_Text.setBounds(160, 60, 120, 20);    //设置"用户名"文本域位置。
        username_Text.grabFocus();//获得光标。
        c.add(username_Text);    //添加"用户名"文本域。

        password_Label = new JLabel("密码:");    //创建"密码"标签。
        password_Label.setBounds(100, 110, 50, 20);
        c.add(password_Label);    //添加"密码"标签。

        password_Text = new JPasswordField();    //创建"密码"文本域。
        password_Text.setBounds(160, 110, 120, 20);    //设置"密码"文本域位置。
        c.add(password_Text);    //添加"密码"文本域。

        repassword_Label = new JLabel("确认密码");    //创建"确认密码"标签。
        repassword_Label.setBounds(100, 162, 70, 20);    //设置"确认密码"标签位置。
        c.add(repassword_Label);    //添加"确认密码"标签。

        repassword_Text = new JPasswordField();    //创建"确认密码"文本域。
        repassword_Text.setBounds(160, 162, 120, 20);    //设置"确认密码"文本域位置。
        c.add(repassword_Text); //添加"确认密码"文本域。

        register_Button = new JButton("注册");    //创建"注册"按钮。
        register_Button.setBounds(90, 210, 60, 20);    //设置"注册"按钮位置。
        //注册"注册"按钮事件监听。
        register_Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = username_Text.getText().trim();//获得用户名。
                String password = new String(password_Text.getPassword());    //获得密码。
                String repassword = new String(repassword_Text.getPassword());    //获得确认密码。
                if (username.equals("")) {
                    JOptionPane.showMessageDialog(jf, "用户名不能为空！", "", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (password.equals("")) {
                    JOptionPane.showMessageDialog(jf, "密码不能为空！", "", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (repassword.equals("")) {
                    JOptionPane.showMessageDialog(jf, "确认密码不能为空！", "", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!password.equals(repassword)) {
                    JOptionPane.showMessageDialog(jf, "两次密码不一致！", "", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                //注册业务处理。
                Customer customer = new Customer();//创建用户对象
                customer.setAccount(username);
                customer.setPassword(password);
                if (AnimalShelterSystem.system.userCatalog.getUser(username) == null) {//注册处理
                    JOptionPane.showMessageDialog(jf, "注册成功！");
                    AnimalShelterSystem.system.userCatalog.addUser(customer);
                    try {
                        AnimalShelterSystem.system.saveDada();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    jf.dispose();//关闭当前窗口
                    try {
                        LoginFrame frame = new LoginFrame();//返回登陆页面。
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(jf, "注册失败！账号已被注册！");
                    Reset();    //重置
                }
            }
        });
        c.add(register_Button);    //添加"注册"按钮。


        cancel_Button = new JButton("取消");    //创建"取消"按钮。
        cancel_Button.setBounds(250, 210, 60, 20);    //设置"取消"按钮位置。
        //注册"取消"按钮事件监听。
        cancel_Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                jf.dispose();//关闭当前页面。
                try {
                    LoginFrame LoginFrame = new LoginFrame();//打开登陆页面。
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        c.add(cancel_Button);    //添加"取消"按钮。

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);    //设置大小不可改变。
        WindowUtil.setFrameCenter(this);//设置窗口居中。
        this.setVisible(true);    //设置窗体可见。
    }

    //重置
    public void Reset() {
        username_Text.setText("");
        password_Text.setText("");
        repassword_Text.setText("");
    }
}