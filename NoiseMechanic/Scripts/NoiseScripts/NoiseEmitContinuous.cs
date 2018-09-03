using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NoiseEmitContinuous : NoiseEmit {

    private bool emitting = false;

	public void EmitStart() {
        emitting = true;
        float timeToWait = GetReceiver().GetTimeToRemove();
        InvokeRepeating("Emit", 0f, timeToWait);
    }

    public void EmitStop() {
        emitting = false;
        CancelInvoke();
    }

    public bool IsEmitting() {
        return emitting;
    }
	
}
