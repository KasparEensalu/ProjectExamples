using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class BarScript : MonoBehaviour {

    [SerializeField]
    private float fill;
    private Image barLeft;
    private Image barRight;
    private Color Lowest = Color.gray;
    private Color Stage1 = Color.white;
    private Color Stage2 = Color.yellow;
    private Color Stage3 = Color.red;

	void Start () {
        barLeft = GameObject.Find("BarFillLeft").GetComponent("Image") as Image;
        barRight = GameObject.Find("BarFillRight").GetComponent("Image") as Image;
    }
	
	void Update () {
        if (fill >= 0.03f) { 
            float randomDeviation = Random.Range(-0.03f, 0.03f);
            barLeft.fillAmount = fill + randomDeviation;
            barRight.fillAmount = fill + randomDeviation;
        } else {
            barLeft.fillAmount = fill;
            barRight.fillAmount = fill;
        }
    }

    public void UpdateNoise(float noise) {
        SetFill(noise);
        SetColor(noise);
    }

    private float TranslateToFillScale(float value) {
        return value / 100;
    }

    private void SetColor(float noise) {
        if (noise <= 120) {
            barLeft.color = Lowest;
            barRight.color = Lowest;
        } else if (noise <= 150) {
            barLeft.color = Stage1;
            barRight.color = Stage1;
        } else if (noise <= 180) {
            barLeft.color = Stage2;
            barRight.color = Stage2;
        } else {
            barLeft.color = Stage3;
            barRight.color = Stage3;
        }
    }
    
    private void SetFill(float noise) {
        float relevantValue = noise - 100;
        if (relevantValue <= 100 && relevantValue >= 0) {
            fill = TranslateToFillScale(relevantValue);
        } else if (relevantValue > 100) {
            fill = 1;
        } else {
            fill = 0;
        }
    }
}
