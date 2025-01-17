package com.addpayafrica.printerappsdk6

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.wisepos.smartpos.WisePosException
import com.wisepos.smartpos.WisePosSdk
import com.wisepos.smartpos.printer.Printer
import com.wisepos.smartpos.printer.PrinterListener
import com.wisepos.smartpos.printer.TextInfo

class PrinterActivity : AppCompatActivity() {

    private  val PRINT_STYLE_LEFT = 0x01 //Print style to the left
    private val PRINT_STYLE_CENTER = 0x02 //print style to the center
    private val PRINT_STYLE_RIGHT = 0x04 //print style to the right

    private var printer : Printer = WisePosSdk.getInstance().printer;
    private var gray = 3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_printer)




       val printButton = findViewById<Button>(R.id.bt_printText)

        printButton.setOnClickListener {

            callPrintText()

        }

    }


    private fun callPrintText(){


        var printEditText = findViewById<EditText>(R.id.et_print_text_input)

        printText()


        printEditText.setText("")//Refresh Logins

    }


    private fun printText() {


        try {

            printer.initPrinter() //Initializing the printer.
            val ret: Int = printer.setGrayLevel(gray) //Set the printer gray value.


            printer.setLineSpacing(1) //Setting Line Spacing.
            val textInfoList: MutableList<TextInfo> = ArrayList()
            //Create the first column string object and set the properties.
            val textInfo1 = TextInfo()
            textInfo1.setFontSize(24)
            textInfo1.setAlign( PRINT_STYLE_LEFT)
            textInfo1.setColumnSpacing(0)
            textInfo1.setWidth(128)
            textInfo1.setBold(true)

            //Create the second column string object and set the properties.
            val textInfo2 = TextInfo()
            textInfo2.setFontSize(24)
            textInfo2.setAlign(PRINT_STYLE_LEFT)
            textInfo2.setColumnSpacing(0)
            textInfo2.setWidth(128)
            textInfo2.setBold(true)

            //Create the third column string object and set the properties.
            val textInfo3 = TextInfo()
            textInfo3.setAlign(PRINT_STYLE_RIGHT)
            textInfo3.setFontSize(24)
            textInfo3.setColumnSpacing(0)
            textInfo3.setWidth(128)
            textInfo3.setBold(true)
            textInfo3.setWithUnderline(true)


            //Heading
            val bundle0 = Bundle()
            bundle0.putString("font", "DEFAULT")
            printer.setPrintFont(bundle0)
            val textInfoLineHeading = TextInfo()
            textInfoLineHeading.setAlign(PRINT_STYLE_CENTER)
            textInfoLineHeading.setFontSize(40)
            printer.setLineSpacing(10)
            textInfoLineHeading.setBold(true)
            textInfoLineHeading.setReverseText(true)
            textInfoLineHeading.setText("CLIENT SUCCESS CASHIER")
            printer.addSingleText(textInfoLineHeading)


            //note:Three string text objects, where the Width and ColumnSpacing configured for each object cannot exceed the paper width of 384.
            textInfo1.setText("ITEM NAME\n")
            textInfoList.add(textInfo1)
            textInfo2.setText("PRICE & NUMBER OF ITEMS")
            textInfoList.add(textInfo2)
            textInfo3.setText("TOTAL PRICE\n")
            textInfoList.add(textInfo3)
            printer.addMultiText(textInfoList)
            textInfoList.clear()


            //Line separator
            val bundle3 = Bundle()
            bundle3.putString("font", "DEFAULT")
            printer.setPrintFont(bundle3)
            val textInfoLine = TextInfo()
            textInfoLine.setAlign(PRINT_STYLE_CENTER)
            textInfoLine.setFontSize(24)
            textInfoLine.setBold(true)
            textInfoLine.setText("--------------------------------------------------")
            printer.addSingleText(textInfoLine)


            textInfo1.setText("Orange\n")
            textInfoList.add(textInfo1)
            textInfo2.setText("5.00*2\n")
            textInfoList.add(textInfo2)
            textInfo3.setText("R10.00\n")
            textInfoList.add(textInfo3)
            printer.addMultiText(textInfoList)
            textInfoList.clear()
            textInfo1.setText("Peache\n")
            textInfoList.add(textInfo1)
            textInfo2.setText("2.00*2\n")
            textInfoList.add(textInfo2)
            textInfo3.setText("R4.00\n")
            textInfoList.add(textInfo3)
            printer.addMultiText(textInfoList)
            textInfoList.clear()
            textInfo1.setText("Bread\n")
            textInfoList.add(textInfo1)
            textInfo2.setText("20.00*1\n")
            textInfoList.add(textInfo2)
            textInfo3.setText("R20.00\n")
            textInfoList.add(textInfo3)
            printer.addMultiText(textInfoList)
            textInfoList.clear()
            textInfo1.setText("Braai pack\n")
            textInfoList.add(textInfo1)
            textInfo2.setText("150.00*1\n")
            textInfoList.add(textInfo2)
            textInfo3.setText("R150.00\n")
            textInfoList.add(textInfo3)
            printer.addMultiText(textInfoList)
            textInfoList.clear()



            //Line separator
            val bundle4 = Bundle()
            bundle4.putString("font", "DEFAULT")
            printer.setPrintFont(bundle4)
            val textInfoLine4 = TextInfo()
            textInfoLine4.setAlign(PRINT_STYLE_CENTER)
            textInfoLine4.setFontSize(24)
            textInfoLine4.setBold(true)
            textInfoLine4.setText("--------------------------------------------------")
            printer.addSingleText(textInfoLine4)

            //Line separator
            val bundle5 = Bundle()
            bundle5.putString("font", "DEFAULT")
            printer.setPrintFont(bundle5)
            val textInfoTotal = TextInfo()
            textInfoTotal.setAlign(PRINT_STYLE_CENTER)
            textInfoTotal.setFontSize(35)
            textInfoTotal.setBold(true)
            textInfoTotal.setText("GRAND TOTAL:")
            printer.addSingleText(textInfoTotal)

            //Line separator
            val bundle6 = Bundle()
            bundle6.putString("font", "DEFAULT")
            printer.setPrintFont(bundle6)
            val textInfoTotalPrice = TextInfo()
            textInfoTotalPrice.setAlign(PRINT_STYLE_CENTER)
            textInfoTotalPrice.setFontSize(30)
            textInfoTotalPrice.setBold(true)
            textInfoTotalPrice.setText("R184")
            textInfoTotalPrice.setWithUnderline(true)
            printer.addSingleText(textInfoTotalPrice)


            val printerOption = Bundle()
            //Start printing
            printer.startPrinting(printerOption, object : PrinterListener {
                override fun onError(i: Int) {

                }

                override fun onFinish() {

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

        }


    }


}