package com.addpayafrica.printerappsdk6

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.addpayafrica.printerappsdk6.Util.autoCleared
import com.addpayafrica.printerappsdk6.databinding.FragmentPrinterBinding
import com.wisepos.smartpos.WisePosException
import com.wisepos.smartpos.WisePosSdk
import com.wisepos.smartpos.errorcode.WisePosErrorCode
import com.wisepos.smartpos.printer.Printer
import com.wisepos.smartpos.printer.PrinterListener
import com.wisepos.smartpos.printer.TextInfo


class PrinterFragment : Fragment(){

    private  val PRINT_STYLE_LEFT = 0x01 //Print style to the left
    private val PRINT_STYLE_CENTER = 0x02 //print style to the center
    private val PRINT_STYLE_RIGHT = 0x04 //print style to the right


    private var binding: FragmentPrinterBinding by autoCleared()
    private lateinit var  printer : Printer
    private lateinit var  mShowResultTv: TextView
    private var gray = 0

    val wisePosSdk = WisePosSdk.getInstance();


    private val bundle = Bundle()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //printer = WisePosSdk.getInstance().printer //Get the Printer object.




        binding = FragmentPrinterBinding.inflate(inflater, container, false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    //    printer =  WisePosSdk.getInstance().printer
    //    printer.initPrinter()

        val context: Context? = context

        var toast = Toast.makeText(context, "Hi Hi", Toast.LENGTH_LONG)
        toast.show()


        //Printer Font
        /*val bundle1 = Bundle()
        printer = WisePosSdk.getInstance().printer
        bundle1.putString("font", "DEFAULT");
        printer.setPrintFont(bundle1);*/

        //Line Spacing
        /* printer = WisePosSdk.getInstance().printer
         printer.setLineSpacing(1);*/

        //Add single Text
        /*val textInfo = TextInfo()
        printer = WisePosSdk.getInstance().printer
        textInfo.setText("www.wiseasy.com\n")
        printer.addSingleText(textInfo)*/

        //Feed Paper
        // printer = WisePosSdk.getInstance().printer
        // printer.feedPaper(24)

        //start printing

        /* val printerOption = Bundle()
        printer = WisePosSdk.getInstance().printer
        printerOption.putInt("feed_len",0)
        printer.startPrinting(printerOption, object : PrinterListener {
            override fun onError(errorCode: Int) {
               // print("printBmp failed" + String.format(" errCode = 0x%x\n", errorCode))
            }

            override fun onFinish() {
               // print("print success\n")
            }

            override fun onReport(i: Int) {
                // Reserved callback method, no functionality required.
            }
        })*/

        try {
            printer.initPrinter() //Initializing the printer.
            val ret: Int = printer.setGrayLevel(gray) //Set the printer gray value.
            if (ret != WisePosErrorCode.ERR_SUCCESS) {
                //  logMsg("startCaching failed" + String.format(" errCode = 0x%x\n", ret))
                return
            }
            val map: Map<String?, Any?>? = printer.printerStatus //Gets the current status of the printer.
            if (map == null) {
                // logMsg("getStatus failed" + String.format(" errCode = 0x%x\n", ret))
                return
            }
            //Gets whether the printer is out of paper from the map file.
            if ((map["paper"].toString().toInt()) == 1){
                //  logMsg("IsHavePaper = false\n")
                return
            } else {
                // logMsg("IsHavePaper = true\n")
            }

            //When printing text information, the program needs to set the printing font. The current setting is the default font.
            val bundle1 = Bundle()
            bundle1.putString("font", "DEFAULT")
            printer.setPrintFont(bundle1)
            val textInfo = TextInfo()
            textInfo.setAlign(PRINT_STYLE_CENTER)
            textInfo.setFontSize(32)
            printer.setLineSpacing(1)
            textInfo.setText("www.wiseasy.com")
            printer.addSingleText(textInfo)
            textInfo.setFontSize(24)
            textInfo.setText("--------------------------------------------")
            printer.addSingleText(textInfo)
            textInfo.setAlign(PRINT_STYLE_LEFT)
            textInfo.setText("Meal Package:KFC $100 coupons")
            printer.addSingleText(textInfo)
            textInfo.setText("Selling Price:$90")
            printer.addSingleText(textInfo)
            textInfo.setText("Merchant Name:KFC（ZS Park）")
            printer.addSingleText(textInfo)
            textInfo.setText("Payment Time:17/3/29 9:27")
            printer.addSingleText(textInfo)
            textInfo.setAlign( PRINT_STYLE_CENTER)
            textInfo.setText("--------------------------------------------")
            printer.addSingleText(textInfo)
            textInfo.setAlign( PRINT_STYLE_LEFT)
            textInfo.setText("NO. of Coupons:5")
            printer.addSingleText(textInfo)
            textInfo.setText("Total Amount:$450")
            printer.addSingleText(textInfo)
            textInfo.setText("SN:1234 4567 4565,")
            printer.addSingleText(textInfo)
            textInfo.setAlign( PRINT_STYLE_CENTER)
            textInfo.setText("--------------------------------------------")
            printer.addSingleText(textInfo)
            textInfo.setText("Bonjour Comment ça va Au revoir!")
            printer.addSingleText(textInfo)
            textInfo.setText("Guten Tag Wie geht's?Auf Wiedersehen.")
            printer.addSingleText(textInfo)
            textInfo.setText(" في متصفحه. عبارة اصفحة ارئيسية تستخدم أيضاً إشا")
            printer.addSingleText(textInfo)
            textInfo.setText("Доброго дня Як справи? Бувайте!")
            printer.addSingleText(textInfo)
            textInfo.setText("გამარჯობა（gamarǰoba）კარგად（kargad）")
            printer.addSingleText(textInfo)
            textInfo.setText("안녕하세요 잘 지내세요 안녕히 가세요!")
            printer.addSingleText(textInfo)
            textInfo.setText("こんにちは お元気ですか またね！")
            printer.addSingleText(textInfo)
            val printerOption = Bundle()
            //Start printing
            printer.startPrinting(printerOption, object : PrinterListener {
                override fun onError(i: Int) {
                    //  logMsg("startPrinting failed errCode = $i")
                }

                override fun onFinish() {
                    //  logMsg("print success\n")
                    try {
                        //After printing, Feed the paper.
                        printer.feedPaper(30)
                    } catch (e: WisePosException) {
                        e.printStackTrace()
                    }
                }

                override fun onReport(i: Int) {
                    //The callback method is reserved and does not need to be implemented
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
            // logMsg("print failed$e\n")
        }




        /* binding.btPrintText.setOnClickListener {



             printText()

         }*/


    }


    /**
     * Print text messages
     */
    private fun printText() {


        //Initialise printer
        printer =  WisePosSdk.getInstance().printer
        printer.initPrinter()

        val context: Context? = context

        var toast = Toast.makeText(context, "Hi Hi", Toast.LENGTH_LONG)
        toast.show()


        //Printer Font
        /*val bundle1 = Bundle()
        printer = WisePosSdk.getInstance().printer
        bundle1.putString("font", "DEFAULT");
        printer.setPrintFont(bundle1);*/

        //Line Spacing
       /* printer = WisePosSdk.getInstance().printer
        printer.setLineSpacing(1);*/

        //Add single Text
        /*val textInfo = TextInfo()
        printer = WisePosSdk.getInstance().printer
        textInfo.setText("www.wiseasy.com\n")
        printer.addSingleText(textInfo)*/

        //Feed Paper
       // printer = WisePosSdk.getInstance().printer
       // printer.feedPaper(24)

        //start printing

        /* val printerOption = Bundle()
        printer = WisePosSdk.getInstance().printer
        printerOption.putInt("feed_len",0)
        printer.startPrinting(printerOption, object : PrinterListener {
            override fun onError(errorCode: Int) {
               // print("printBmp failed" + String.format(" errCode = 0x%x\n", errorCode))
            }

            override fun onFinish() {
               // print("print success\n")
            }

            override fun onReport(i: Int) {
                // Reserved callback method, no functionality required.
            }
        })*/

        try {
            printer.initPrinter() //Initializing the printer.
            val ret: Int = printer.setGrayLevel(gray) //Set the printer gray value.
            if (ret != WisePosErrorCode.ERR_SUCCESS) {
              //  logMsg("startCaching failed" + String.format(" errCode = 0x%x\n", ret))
                return
            }
            val map: Map<String?, Any?>? = printer.printerStatus //Gets the current status of the printer.
            if (map == null) {
               // logMsg("getStatus failed" + String.format(" errCode = 0x%x\n", ret))
                return
            }
            //Gets whether the printer is out of paper from the map file.
            if ((map["paper"].toString().toInt()) == 1){
              //  logMsg("IsHavePaper = false\n")
                return
            } else {
               // logMsg("IsHavePaper = true\n")
            }

            //When printing text information, the program needs to set the printing font. The current setting is the default font.
            val bundle1 = Bundle()
            bundle1.putString("font", "DEFAULT")
            printer.setPrintFont(bundle1)
            val textInfo = TextInfo()
            textInfo.setAlign(PRINT_STYLE_CENTER)
            textInfo.setFontSize(32)
            printer.setLineSpacing(1)
            textInfo.setText("www.wiseasy.com")
            printer.addSingleText(textInfo)
            textInfo.setFontSize(24)
            textInfo.setText("--------------------------------------------")
            printer.addSingleText(textInfo)
            textInfo.setAlign(PRINT_STYLE_LEFT)
            textInfo.setText("Meal Package:KFC $100 coupons")
            printer.addSingleText(textInfo)
            textInfo.setText("Selling Price:$90")
            printer.addSingleText(textInfo)
            textInfo.setText("Merchant Name:KFC（ZS Park）")
            printer.addSingleText(textInfo)
            textInfo.setText("Payment Time:17/3/29 9:27")
            printer.addSingleText(textInfo)
            textInfo.setAlign( PRINT_STYLE_CENTER)
            textInfo.setText("--------------------------------------------")
            printer.addSingleText(textInfo)
            textInfo.setAlign( PRINT_STYLE_LEFT)
            textInfo.setText("NO. of Coupons:5")
            printer.addSingleText(textInfo)
            textInfo.setText("Total Amount:$450")
            printer.addSingleText(textInfo)
            textInfo.setText("SN:1234 4567 4565,")
            printer.addSingleText(textInfo)
            textInfo.setAlign( PRINT_STYLE_CENTER)
            textInfo.setText("--------------------------------------------")
            printer.addSingleText(textInfo)
            textInfo.setText("Bonjour Comment ça va Au revoir!")
            printer.addSingleText(textInfo)
            textInfo.setText("Guten Tag Wie geht's?Auf Wiedersehen.")
            printer.addSingleText(textInfo)
            textInfo.setText(" في متصفحه. عبارة اصفحة ارئيسية تستخدم أيضاً إشا")
            printer.addSingleText(textInfo)
            textInfo.setText("Доброго дня Як справи? Бувайте!")
            printer.addSingleText(textInfo)
            textInfo.setText("გამარჯობა（gamarǰoba）კარგად（kargad）")
            printer.addSingleText(textInfo)
            textInfo.setText("안녕하세요 잘 지내세요 안녕히 가세요!")
            printer.addSingleText(textInfo)
            textInfo.setText("こんにちは お元気ですか またね！")
            printer.addSingleText(textInfo)
            val printerOption = Bundle()
            //Start printing
            printer.startPrinting(printerOption, object : PrinterListener {
                override fun onError(i: Int) {
                  //  logMsg("startPrinting failed errCode = $i")
                }

                override fun onFinish() {
                  //  logMsg("print success\n")
                    try {
                        //After printing, Feed the paper.
                        printer.feedPaper(30)
                    } catch (e: WisePosException) {
                        e.printStackTrace()
                    }
                }

                override fun onReport(i: Int) {
                    //The callback method is reserved and does not need to be implemented
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
           // logMsg("print failed$e\n")
        }




    }



}

