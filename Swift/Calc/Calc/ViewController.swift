//
//  ViewController.swift
//  Calc
//
//  Created by Haotian Zhang on 20/6/2015.
//  Copyright © 2015 HTPLEX. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var display: UILabel!
    
    var userIsIntheMiddleOfTyping: Bool = false

    @IBAction func appendDigit(sender: UIButton) {
        let digit = sender.currentTitle!
        if userIsIntheMiddleOfTyping{
            
        display.text = display.text! + digit
       // print("digit= \(digit)");
        }
        else{
            display.text = digit
            userIsIntheMiddleOfTyping = true
        }
    }
    @IBAction func operate(sender: UIButton) {
        if userIsIntheMiddleOfTyping{
            enter()
        }
        let operation = sender.currentTitle!
        switch operation{
        case "×" :performOperation{$0 * $1}
        case "÷" :performOperation{$1 / $0}
        case "+" :performOperation{$0 + $1}
        case "−" :performOperation{$0 - $1}
        case "√" :performOperation2{sqrt($0)}
        case "sin" : performOperation2{sin($0)}
        case "cos" : performOperation2{sin($0)}
        case "π" : performOperation2{$0*M_PI}
        default: break
        }
    }
    var oprandStack = Array<Double>()

    
    func performOperation(operation: (Double, Double) -> Double){
        if oprandStack.count>=2{
            displayValue=operation(oprandStack.removeLast(), oprandStack.removeLast())
            enter()
        }
    }
    
    func performOperation2(operation: Double -> Double){
        if oprandStack.count>=1 {
            displayValue=operation(oprandStack.removeLast())
            enter()
        }
    }
    
    
    @IBAction func enter() {
        userIsIntheMiddleOfTyping=false
        oprandStack.append(displayValue)
        print("oprandStack = \(oprandStack)")  
        
        //display.text = "0"
    }
    @IBAction func clear() {
        enter()
        oprandStack.removeAll()
        print("oprandStack = \(oprandStack)")
    }

    
    var displayValue: Double{
        set{
            display.text = "\(newValue)"
        }
        get{
            return NSNumberFormatter().numberFromString(display.text!)!.doubleValue
        }
    }

}

