using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TestLevelPlatformController : MonoBehaviour {

    public GameObject platform;
    public GameObject character;
    public float size;
    public List<GameObject> noiseSources;

    public void Awake()
    {
        GameObject[] allSources = GameObject.FindGameObjectsWithTag("NoiseSource");
        for (int i=0; i < allSources.Length; i++)
        {
            if (GameObjectOnPlatform(allSources[i]))
            {
                noiseSources.Add(allSources[i]);
            }
        } 
    }

    void Update () {
        if (Input.GetMouseButtonDown(0) && CharacterOnPlatform())
        {
            PlayNoise();
        }
    }

    public bool CharacterOnPlatform()
    {
        return (Mathf.Abs(platform.transform.position.x - character.transform.position.x) <= size) && (Mathf.Abs(platform.transform.position.z - character.transform.position.z) <= size);
    }

    public bool GameObjectOnPlatform(GameObject gameObject)
    {
        return (Mathf.Abs(platform.transform.position.x - gameObject.transform.position.x) <= size) && (Mathf.Abs(platform.transform.position.z - gameObject.transform.position.z) <= size);
    }

    private void PlayNoise()
    {
        foreach (GameObject noiseSource in noiseSources)
        {
            NoiseEmit emitter = noiseSource.GetComponent("NoiseEmit") as NoiseEmit;
            AudioSource audio = noiseSource.GetComponent("AudioSource") as AudioSource;
            emitter.Emit();
            audio.Play();
        }
    }
}
