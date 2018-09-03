using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TextController : MonoBehaviour {

    private string generalText = 
@"Step onto 1 of the 3 platforms in front of you to test the stages of the game mechanic.

Gray platform:
Stage 1 of the mechanic- Fright stage

Yellow platform:
Stage 2 of the mechanic-
Acoustic shock stage

Red platform:
Stage 3 of the mechanic-
Eardum rupture stage

Additionally there are 3 blue platforms with noise sources of configurable loudness to play around with.

Walk on any platform for further instructions.

Press 'Escape' to exit the TestLevel";
    private string stage1Text =
@"-Fright stage- 
The sphere in the middle of the platform is simulating a noise source with a pressure of 180dB at 10cm, equivalent to a typical shotgun blast.

Click the left mouse button to produce the noise.";
    private string stage2Text =
@"-Acoustic shock stage- 
The sphere in the middle of the platform is simulating a noise source with a pressure of 210dB at 10cm, equivalent to an M3 recoilless rifle(also known as the Bazooka) blast.

Click the left mouse button to produce the noise.";
    private string stage3Text =
@"-Eardrum rupture stage- 
The sphere in the middle of the platform is simulating a noise source with a pressure of 235dB at 10cm, equivalent to an small explosion.

Click the left mouse button to produce the noise.";
    private string multipleConfText =
@"There are 11 noise sources with configurable pressure on this platform.

Press:
F1 - 150dB
F2 - 160dB
...
F9 - 240dB
F10 - 250dB

Left mouse button - produce noise from all sources ";
    private string impactConfText =
@"There is an impact noise source with configurable pressure on this platform.

Press:
F1 - 150dB
F2 - 160dB
...
F9 - 240dB
F10 - 250dB

Left mouse button - produce noise";
    private string contConfText =
@"There is continuous noise source with configurable pressure on this platform.

Press:
F1 - 150dB
F2 - 160dB
...
F9 - 240dB
F10 - 250dB

Left mouse button - toggle noise on/off";

    public UnityEngine.UI.Text text;
    public GameObject character;
    public NoiseMechanicController controller;
    public GameObject stage1;
    public GameObject stage2;
    public GameObject stage3;
    public GameObject multipleConf;
    public GameObject impactConf;
    public GameObject contConf;

    private void Start()
    {
        controller = character.GetComponent("NoiseMechanicController") as NoiseMechanicController;
    }
 
	void Update () {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            Application.Quit();
        }
        if (CharacterOnPlatform(stage1, 5) && !controller.IsPlaying()) {
            text.text = stage1Text;
        } else if (CharacterOnPlatform(stage2, 5) && !controller.IsPlaying()) {
            text.text = stage2Text;
        } else if(CharacterOnPlatform(stage3, 5) && !controller.IsPlaying()) {
            text.text = stage3Text;
        } else if (CharacterOnPlatform(multipleConf, 20) && !controller.IsPlaying()) {
            text.text = multipleConfText;
        } else if (CharacterOnPlatform(impactConf, 8) && !controller.IsPlaying()) {
            text.text = impactConfText;
        } else if (CharacterOnPlatform(contConf, 8) && !controller.IsPlaying()) {
            text.text = contConfText;
        } else if (!controller.IsPlaying()) {
            text.text = generalText;
        } else {
            text.text = "";
        }
	}

    public bool CharacterOnPlatform(GameObject platform, float size) {
        return (Mathf.Abs(platform.transform.position.x - character.transform.position.x) <= size) && (Mathf.Abs(platform.transform.position.z - character.transform.position.z) <= size);
    }
}
