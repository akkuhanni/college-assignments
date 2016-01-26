using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using System.IO.Ports;

namespace Demo
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            
            SerialPort port = new SerialPort();
            port.BaudRate = 9600;
            port.PortName = "COM15";
            port.Open();
            while(true)
            {
                String data = port.ReadLine();
                int value = Int32.Parse(data);
                Console.WriteLine("value = "+value);
                if (value == 1)
                    break;
              
            } 

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            MainForm mf= new MainForm();
            Application.Run(mf);
            mf.Capture_image();
        }
    }
}
